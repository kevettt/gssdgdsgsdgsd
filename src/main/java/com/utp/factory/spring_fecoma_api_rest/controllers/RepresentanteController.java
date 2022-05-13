package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Representante;
import com.utp.factory.spring_fecoma_api_rest.services.IRepresentanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Representante> crearCategoria(@RequestBody Representante representante) {

        return ResponseEntity.ok(iRepresentanteService.save(representante));
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
