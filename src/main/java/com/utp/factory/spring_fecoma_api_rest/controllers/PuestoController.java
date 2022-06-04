package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Categoria;
import com.utp.factory.spring_fecoma_api_rest.entities.Puesto;
import com.utp.factory.spring_fecoma_api_rest.services.ICategoriaService;
import com.utp.factory.spring_fecoma_api_rest.services.IPuestoService;
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
@RequestMapping(value ="/api/v1/puesto/")
public class PuestoController {

    @Autowired
    private IPuestoService iPuestoService;

    @GetMapping("/list")
    public ResponseEntity<List<Puesto>> fineAll(){

        return ResponseEntity.ok(iPuestoService.fineAll());
    }

    @GetMapping("/pagina/{page}")
    public ResponseEntity<Page<Puesto>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iPuestoService.paginacion(PageRequest.of(page,10)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Puesto> getPuesto(@PathVariable Long id){
        return ResponseEntity.ok(iPuestoService.find(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearPuesto(@Valid @RequestBody Puesto puesto, BindingResult result) {
        Puesto puesto1 = new Puesto();
        Map<String,Object> response = new HashMap<String, Object>();
        if(result.hasErrors()) {
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

        }
        try {
            puesto1 = iPuestoService.save(puesto);
        }catch (DataAccessException e){
            response.put("mensaje","error al crear el puesto");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMostSpecificCause().getMessage()));
        }
        response.put("puesto",puesto1);
        response.put("mensaje","Se creo el puesto con exito");


        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Puesto> editCategoria(@PathVariable Long id,@RequestBody Puesto puesto){
        Puesto puesto1 = iPuestoService.find(id);
        puesto1.setDescripcion(puesto.getDescripcion());
        puesto1.setPuesto(puesto.getPuesto());
        puesto1.setSueldo(puesto.getSueldo());
        return ResponseEntity.ok(iPuestoService.edit(puesto1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCateoria(@PathVariable Long id) {
        iPuestoService.eliminar(id);
        return new ResponseEntity<>("Puesto eliminado", HttpStatus.OK);
    }
}
