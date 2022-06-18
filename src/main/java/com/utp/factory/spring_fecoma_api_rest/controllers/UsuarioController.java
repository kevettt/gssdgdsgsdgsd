package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.security.entities.Role;
import com.utp.factory.spring_fecoma_api_rest.security.entities.Usuario;
import com.utp.factory.spring_fecoma_api_rest.security.enums.RolNombre;
import com.utp.factory.spring_fecoma_api_rest.security.services.IRolService;
import com.utp.factory.spring_fecoma_api_rest.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/empleado/")
public class UsuarioController {


    @Autowired
    private IUsuarioService iUsuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IRolService rolService;

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

    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@Valid @RequestBody Usuario nuevousuario, BindingResult result) {
        Map<String,Object> response = new HashMap<>();

        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(iUsuarioService.existsByUsername(nuevousuario.getUsername())){
            response.put("mensaje", "El nombre de usuario ya esta en uso");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(iUsuarioService.existsByCorreo(nuevousuario.getCorreo())){
            response.put("mensaje", "El email ya esta en uso");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Usuario usuario = new Usuario(nuevousuario.getNombre(), nuevousuario.getApellido(),nuevousuario.getDireccion(),nuevousuario.getDni(),nuevousuario.getTelefono(),nuevousuario.getCorreo(), nuevousuario.getUsername(),passwordEncoder.encode(nuevousuario.getPassword()),true);
            Set<Role> roles = new HashSet<>();
            roles.add(rolService.getByRol(RolNombre.ROLE_VENDEDOR).get());
            if(nuevousuario.getRoles().contains("almacenero"))
                roles.add(rolService.getByRol(RolNombre.ROLE_ALMACENERO).get());
            usuario.setRoles(roles);
            if(nuevousuario.getRoles().contains("admin"))
                roles.add(rolService.getByRol(RolNombre.ROLE_ADMIN).get());
            usuario.setRoles(roles);
            iUsuarioService.save(usuario);
            response.put("empleado", usuario);
            response.put("mensaje", "Se creao el usuario con exito");
        }catch (DataAccessException e){
            response.put("mensaje", "error al crear el usuario");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));

        }

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
        usuario1.setEnabled(usuario.getEnabled());
        return ResponseEntity.ok(iUsuarioService.edit(usuario1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id){
        iUsuarioService.eliminar(id);
        return  new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
    }
}
