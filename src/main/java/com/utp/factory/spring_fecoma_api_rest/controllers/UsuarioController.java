package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.security.entities.Usuario;
import com.utp.factory.spring_fecoma_api_rest.services.IUsuarioService;
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
public class UsuarioController {


    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping("/lista")//listar Empleados
    public ResponseEntity<List<Usuario>> fineAll(){

        return ResponseEntity.ok(iUsuarioService.fineAll());
    }
    @GetMapping("/pagina/{page}")//paginacion
    public ResponseEntity<Page<Usuario>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iUsuarioService.paginacion(PageRequest.of(page, 10)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getEmpleado(@PathVariable Long id){
        return  ResponseEntity.ok(iUsuarioService.find(id));
    }

    /*crear sin validacion
    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearEmpleado(@RequestBody Usuario empleado) {

        return ResponseEntity.ok(iUsuarioService.save(empleado));
    }*/

    //Crear con validacion
    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@Valid @RequestBody Usuario usuario, BindingResult result) {
        Usuario usuario1 = new Usuario();
        var response = new HashMap<String, Object>();
        if (result.hasErrors()){
            var errors = result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            usuario1 = iUsuarioService.save(usuario);
        }catch (DataAccessException e){
            response.put("mensaje", "error al crear el usuario");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));

        }
        response.put("empleado", usuario1);
        response.put("mensaje", "Se creao el usuario con exito");


        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Usuario> editEmpleado(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuario1 = iUsuarioService.find(id);
        usuario1.setNombre(usuario.getNombre());
        usuario1.setApellido(usuario.getApellido());
        usuario1.setDni(usuario.getDni());
        usuario1.setDireccion(usuario.getDireccion());
        usuario1.setTelefono(usuario.getTelefono());
        usuario1.setPuesto(usuario.getPuesto());
        usuario1.setCorreo(usuario.getCorreo());
        return ResponseEntity.ok(iUsuarioService.edit(usuario1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id){
        iUsuarioService.eliminar(id);
        return  new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
    }
}
