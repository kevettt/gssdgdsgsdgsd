package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Representante;
import com.utp.factory.spring_fecoma_api_rest.services.IRepresentanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/representante/")
@CrossOrigin
public class RepresentanteController {
    @Autowired
    private IRepresentanteService iRepresentanteService;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/list")
    public ResponseEntity<List<Representante>> fineAll(){

        return ResponseEntity.ok(iRepresentanteService.fineAll());
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/pagina/{page}")
    public ResponseEntity<Page<Representante>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iRepresentanteService.paginacion(PageRequest.of(page,4)));
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/{id}")
    public ResponseEntity<?> getRepresentante(@PathVariable Long id){
        var response = new HashMap<String, Object>();
        try {
            response.put("mensaje","Representante : ".concat(id.toString().concat("encontrado")));

        }catch (Exception e){
            response.put("mensaje","error al buscar el representante");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMessage()));
        }
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/crear")
    public ResponseEntity<?> crearRepresentante(@Valid @RequestBody Representante representante, BindingResult result) {
        Representante representante1 = new Representante();
        var response = new HashMap<String, Object>();
        if(result.hasErrors()) {
            var errors= result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

        }
        try {
            representante1 = iRepresentanteService.save(representante);
            response.put("representante",representante1);
            response.put("mensaje","Se creo el representante con exito");
        }catch (DataAccessException e){
            response.put("mensaje","error al crear el representante");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMostSpecificCause().getMessage()));
        }


        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editRepresentante(@Valid @RequestBody Representante representante, BindingResult result,@PathVariable Long id){
        Representante representanteactual = new Representante();
        Representante representanteactualizado = new Representante();
        representanteactual = iRepresentanteService.find(id);
        var response = new HashMap<String, Object>();

        if(result.hasErrors()) {
            var errors= result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

        }
        if(representanteactual==null){
            response.put("mensaje","el representan con el id: ".concat(id.toString().concat("no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try {
            representanteactual.setNombres(representante.getNombres());
            representanteactual.setApellidos(representante.getApellidos());
            representanteactual.setCorreo(representante.getCorreo());
            representanteactual.setTelefono(representante.getTelefono());
            representanteactualizado = iRepresentanteService.edit(representanteactual);
            response.put("representante",representanteactualizado);
            response.put("mensaje","se actualizo el representante correctamente");

        }catch (DataAccessException e ){
            response.put("mensaje","error al actualizar el representante");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarRepresentante(@PathVariable Long id) {
        var response = new HashMap<String, Object>();
        try {
            response.put("mensaje","Representante elimnado");
            iRepresentanteService.eliminar(id);

        }catch (Exception e){
            response.put("mensaje","error al eliminar al representante");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMessage()));
        }

        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }
}
