package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.TipoOperacion;
import com.utp.factory.spring_fecoma_api_rest.services.ITipoOperacionService;
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
public class TipoOperacionController {
    @Autowired
    private ITipoOperacionService iTipoOperacionService;

    @GetMapping("/list")
    public ResponseEntity<List<TipoOperacion>> fineAll(){

        return ResponseEntity.ok(iTipoOperacionService.fineAll());
    }

    @GetMapping("/pagina/{page}")
    public ResponseEntity<Page<TipoOperacion>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iTipoOperacionService.paginacion(PageRequest.of(page,10)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoOperacion> getMotivo(@PathVariable Long id){
        return ResponseEntity.ok(iTipoOperacionService.find(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearMotivo(@Valid @RequestBody TipoOperacion tipoOperacion, BindingResult result) {
        TipoOperacion tipoOperacion1 = new TipoOperacion();
        Map<String,Object> response = new HashMap<String, Object>();
        if(result.hasErrors()) {
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

        }
        try {
            tipoOperacion1 = iTipoOperacionService.save(tipoOperacion);
        }catch (DataAccessException e){
            response.put("mensaje","error al crear el tipoOperacion");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMostSpecificCause().getMessage()));
        }
        response.put("motivo", tipoOperacion1);
        response.put("mensaje","Se creo el tipoOperacion con exito");


        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TipoOperacion> editMotivo(@PathVariable Long id, @RequestBody TipoOperacion tipoOperacion){
        TipoOperacion tipoOperacion1 = iTipoOperacionService.find(id);
        tipoOperacion1.setMotivo(tipoOperacion.getMotivo());
        return ResponseEntity.ok(iTipoOperacionService.edit(tipoOperacion1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCateoria(@PathVariable Long id) {
        iTipoOperacionService.eliminar(id);
        return new ResponseEntity<>("TipoOperacion eliminado", HttpStatus.OK);
    }
}
