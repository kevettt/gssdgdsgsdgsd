package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Categoria;
import com.utp.factory.spring_fecoma_api_rest.entities.Puesto;
import com.utp.factory.spring_fecoma_api_rest.services.ICategoriaService;
import com.utp.factory.spring_fecoma_api_rest.services.IPuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<Puesto> crearPuesto(@RequestBody Puesto puesto) {

        return ResponseEntity.ok(iPuestoService.save(puesto));
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
