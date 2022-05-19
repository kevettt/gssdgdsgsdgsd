package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Motivo;
import com.utp.factory.spring_fecoma_api_rest.services.IMotivoService;
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
@RequestMapping(value ="/api/v1/motivo/")
public class MotivoController {
    @Autowired
    private IMotivoService iMotivoService;

    @GetMapping("/list")
    public ResponseEntity<List<Motivo>> fineAll(){

        return ResponseEntity.ok(iMotivoService.fineAll());
    }

    @GetMapping("/pagina/{page}")
    public ResponseEntity<Page<Motivo>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iMotivoService.paginacion(PageRequest.of(page,10)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motivo> getMotivo(@PathVariable Long id){
        return ResponseEntity.ok(iMotivoService.find(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearMotivo(@Valid @RequestBody Motivo motivo, BindingResult result) {
        Motivo motivo1 = new Motivo();
        var response = new HashMap<String, Object>();
        if(result.hasErrors()) {
            var errors= result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

        }
        try {
            motivo1 = iMotivoService.save(motivo);
        }catch (DataAccessException e){
            response.put("mensaje","error al crear el motivo");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMostSpecificCause().getMessage()));
        }
        response.put("motivo",motivo1);
        response.put("mensaje","Se creo el motivo con exito");


        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Motivo> editMotivo(@PathVariable Long id,@RequestBody Motivo motivo){
        Motivo motivo1 = iMotivoService.find(id);
        motivo1.setMotivo(motivo.getMotivo());
        return ResponseEntity.ok(iMotivoService.edit(motivo1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCateoria(@PathVariable Long id) {
        iMotivoService.eliminar(id);
        return new ResponseEntity<>("Motivo eliminado", HttpStatus.OK);
    }
}
