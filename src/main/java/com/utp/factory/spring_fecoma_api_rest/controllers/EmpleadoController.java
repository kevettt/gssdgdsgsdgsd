package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Empleado;
import com.utp.factory.spring_fecoma_api_rest.entities.Proveedor;
import com.utp.factory.spring_fecoma_api_rest.services.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/empleado/")
public class EmpleadoController {


    @Autowired
    private IEmpleadoService iEmpleadoService;

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

    @PostMapping("/crear")
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {

        return ResponseEntity.ok(iEmpleadoService.save(empleado));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Empleado> editEmpleado(@PathVariable Long id,@RequestBody Empleado empleado){
        Empleado empleado1 = iEmpleadoService.find(id);
        empleado1.setNombre(empleado.getNombre());
        empleado1.setApellido(empleado.getApellido());
        empleado1.setPuesto(empleado.getPuesto());
        return ResponseEntity.ok(iEmpleadoService.edit(empleado1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable Long id){
        iEmpleadoService.eliminar(id);
        return  new ResponseEntity<>("Proveedor eliminado", HttpStatus.OK);
    }
}
