package com.utp.factory.spring_fecoma_api_rest.controllers;


import com.utp.factory.spring_fecoma_api_rest.entities.Kardex;
import com.utp.factory.spring_fecoma_api_rest.services.IKardexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api/v1/kardex/")
public class KardexController {

    @Autowired
    private IKardexService iKardexService;

    @GetMapping("/list")
    public ResponseEntity<List<Kardex>> fineAll(){

        return ResponseEntity.ok(iKardexService.fineAll());
    }

    @GetMapping("/pagina/{page}")
    public ResponseEntity<Page<Kardex>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iKardexService.paginacion(PageRequest.of(page,10)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kardex> getKardex(@PathVariable Long id){
        return ResponseEntity.ok(iKardexService.find(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<Kardex> crearKardex(@RequestBody Kardex kardex) {

        return ResponseEntity.ok(iKardexService.save(kardex));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Kardex> editKardex(@PathVariable Long id,@RequestBody Kardex kardex){
        Kardex kardex1 = iKardexService.find(id);
        kardex1.setEmpleado(kardex.getEmpleado());
        kardex1.setMotivo(kardex.getMotivo());
        kardex1.setFecha(kardex.getFecha());
        kardex1.setMonto(kardex.getMonto());
        return ResponseEntity.ok(iKardexService.edit(kardex1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarKardex(@PathVariable Long id) {
        iKardexService.eliminar(id);
        return new ResponseEntity<>("Kardex eliminado", HttpStatus.OK);
    }

}
