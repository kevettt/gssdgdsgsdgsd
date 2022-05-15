package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Empleado;
import com.utp.factory.spring_fecoma_api_rest.services.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/empleado/")
public class EmpleadoController {


    @Autowired
    private IEmpleadoService iEmpleadoService;

    @GetMapping("/lista")//listar Empleados
    public ResponseEntity<List<Empleado>> fineAll(){

        return ResponseEntity.ok(iEmpleadoService.fineAll());
    }
    @GetMapping("/pagina/{page}")//paginacion
    public ResponseEntity<Page<Empleado>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iEmpleadoService.paginacion(PageRequest.of(page, 10)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable Long id){
        return  ResponseEntity.ok(iEmpleadoService.find(id));
    }

    /*crear sin validacion
    @PostMapping("/crear")
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {

        return ResponseEntity.ok(iEmpleadoService.save(empleado));
    }*/

    //Crear con validacion
    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@Valid @RequestBody Empleado empleado, BindingResult result) {
        Empleado empleado1 = new Empleado();
        var response = new HashMap<String, Object>();
        if (result.hasErrors()){
            var errors = result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            empleado1 = iEmpleadoService.save(empleado);
        }catch (DataAccessException e){
            response.put("mensaje", "error al crear el empleado");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));

        }
        response.put("empleado", empleado1);
        response.put("mensaje", "Se creao el empleado con exito");


        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Empleado> editEmpleado(@PathVariable Long id,@RequestBody Empleado empleado){
        Empleado empleado1 = iEmpleadoService.find(id);
        empleado1.setNombre(empleado.getNombre());
        empleado1.setApellido(empleado.getApellido());
        empleado1.setDni(empleado.getDni());
        empleado1.setDireccion(empleado.getDireccion());
        empleado1.setTelefono(empleado.getTelefono());
        empleado1.setPuesto(empleado.getPuesto());
        empleado1.setCorreo(empleado.getCorreo());
        return ResponseEntity.ok(iEmpleadoService.edit(empleado1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id){
        iEmpleadoService.eliminar(id);
        return  new ResponseEntity<>("Empleado eliminado", HttpStatus.OK);
    }
}
