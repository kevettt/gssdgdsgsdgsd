package com.utp.factory.spring_fecoma_api_rest.controllers;


import com.utp.factory.spring_fecoma_api_rest.entities.Categoria;
import com.utp.factory.spring_fecoma_api_rest.services.ICategoriaService;
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
@RequestMapping(value ="/api/v1/categoria/")
@CrossOrigin
public class CategoriaController {


    @Autowired
    private ICategoriaService iCategoriaService;

    @GetMapping("/list")
    public ResponseEntity<List<Categoria>>fineAll(){
        return ResponseEntity.ok(iCategoriaService.fineAll());
    }

    @GetMapping("/pagina/{page}")
    public ResponseEntity<Page<Categoria>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iCategoriaService.paginacion(PageRequest.of(page,10)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable Long id){
        return ResponseEntity.ok(iCategoriaService.find(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCategoria(@Valid @RequestBody Categoria categoria, BindingResult result) {
        Categoria categoria1 = new Categoria();
        var response = new HashMap<String, Object>();
        if(result.hasErrors()) {
            var errors= result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

        }
        try {
            categoria1 = iCategoriaService.save(categoria);
        }catch (DataAccessException e){
            response.put("mensaje","error al crear el categoria");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMostSpecificCause().getMessage()));
        }
        response.put("categoria",categoria1);
        response.put("mensaje","Se creo el categoria con exito");


        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Categoria> editCategoria(@PathVariable Long id,@RequestBody Categoria categoria){
        Categoria categoria1 = iCategoriaService.find(id);
        categoria1.setNombre(categoria.getNombre());
        categoria1.setDescripcion(categoria.getDescripcion());
        return ResponseEntity.ok(iCategoriaService.edit(categoria1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCateoria(@PathVariable Long id) {
        iCategoriaService.eliminar(id);
        return new ResponseEntity<>("Categoria eliminado", HttpStatus.OK);
    }

}
