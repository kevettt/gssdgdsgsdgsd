package com.utp.factory.spring_fecoma_api_rest.controllers;


import com.utp.factory.spring_fecoma_api_rest.entities.Categoria;
import com.utp.factory.spring_fecoma_api_rest.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api/v1/categoria/")
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
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {

        return ResponseEntity.ok(iCategoriaService.save(categoria));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Categoria> editCategoria(@PathVariable Long id,@RequestBody Categoria categoria){
        Categoria categoria1 = iCategoriaService.find(id);
        categoria1.setDescripcion(categoria.getDescripcion());
        return ResponseEntity.ok(iCategoriaService.edit(categoria1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCateoria(@PathVariable Long id) {
        iCategoriaService.eliminar(id);
        return new ResponseEntity<>("Categoria eliminado", HttpStatus.OK);
    }

}
