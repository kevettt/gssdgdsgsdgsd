package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Representante;
import com.utp.factory.spring_fecoma_api_rest.services.IRepresentanteService;
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
@RequestMapping(value = "/api/v1/representante/")
public class RepresentanteController {
    @Autowired
    private IRepresentanteService iRepresentanteService;

    @GetMapping("/list")
    public ResponseEntity<List<Representante>> fineAll(){

        return ResponseEntity.ok(iRepresentanteService.fineAll());
    }

    @GetMapping("/pagina/{page}")
    public ResponseEntity<Page<Representante>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iRepresentanteService.paginacion(PageRequest.of(page,10)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Representante> getCategoria(@PathVariable Long id){
        return ResponseEntity.ok(iRepresentanteService.find(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCategoria(@Valid @RequestBody Representante representante, BindingResult result) {
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
        }catch (DataAccessException e){
            response.put("mensaje","error al crear el representante");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMostSpecificCause().getMessage()));
        }
        response.put("representante",representante1);
        response.put("mensaje","Se creo el representante con exito");


        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Representante> editCategoria(@PathVariable Long id,@RequestBody Representante representante){
        Representante representante1 = iRepresentanteService.find(id);
        representante1.setNombres(representante.getNombres());
        representante1.setApellidos(representante.getApellidos());
        representante1.setCorreo(representante.getCorreo());
        representante1.setTelefono(representante.getTelefono());
        return ResponseEntity.ok(iRepresentanteService.edit(representante1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCateoria(@PathVariable Long id) {
        iRepresentanteService.eliminar(id);
        return new ResponseEntity<>("Categoria eliminado", HttpStatus.OK);
    }
}
