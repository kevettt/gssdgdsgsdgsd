package com.utp.factory.spring_fecoma_api_rest.security.controller;

import com.utp.factory.spring_fecoma_api_rest.security.entities.Role;
import com.utp.factory.spring_fecoma_api_rest.security.entities.Usuario;
import com.utp.factory.spring_fecoma_api_rest.security.services.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
@RequestMapping("/api/v1/rol")
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping("/lista")
    public ResponseEntity<List<Role>> getAll(){
        return new ResponseEntity<>(rolService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@Valid @RequestBody Role role, BindingResult result) {
        Role newrole;
        Map<String,Object> response = new HashMap<>();

        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            newrole = rolService.save(role);
            response.put("rol", newrole);
            response.put("mensaje", "Se creado el rol con exito");
        }catch (DataAccessException e){
            response.put("mensaje", "error al crear el rol");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));

        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
