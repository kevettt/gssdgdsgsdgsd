package com.utp.factory.spring_fecoma_api_rest.controllers;


import com.utp.factory.spring_fecoma_api_rest.entities.DetalleKardex;
import com.utp.factory.spring_fecoma_api_rest.services.IDetalleKardexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api/v1/detalle/")
public class DetalleController {
    @Autowired
    private IDetalleKardexService iKardexService;

    @GetMapping("/list")
    public ResponseEntity<List<DetalleKardex>> fineAll(){

        return ResponseEntity.ok(iKardexService.fineAll());
    }

    @GetMapping("/pagina/{page}")
    public ResponseEntity<Page<DetalleKardex>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iKardexService.paginacion(PageRequest.of(page,10)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleKardex> getDetalleKardex(@PathVariable Long id){
        return ResponseEntity.ok(iKardexService.find(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<DetalleKardex> crearDetalleKardex(@RequestBody DetalleKardex kardex) {

        return ResponseEntity.ok(iKardexService.save(kardex));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DetalleKardex> editDetalleKardex(@PathVariable Long id,@RequestBody DetalleKardex kardex){
        DetalleKardex kardex1 = iKardexService.find(id);
        kardex1.setProducto(kardex.getProducto());
        kardex1.setCantidad(kardex.getCantidad());
        kardex1.setMonto(kardex.getMonto());
        return ResponseEntity.ok(iKardexService.edit(kardex1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDetalleKardex(@PathVariable Long id) {
        iKardexService.eliminar(id);
        return new ResponseEntity<>("DetalleKardex eliminado", HttpStatus.OK);
    }
}
