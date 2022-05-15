package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Motivo;
import com.utp.factory.spring_fecoma_api_rest.services.IMotivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Motivo> crearMotivo(@RequestBody Motivo motivo) {

        return ResponseEntity.ok(iMotivoService.save(motivo));
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
