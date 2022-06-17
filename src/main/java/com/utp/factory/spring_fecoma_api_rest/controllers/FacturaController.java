package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Factura;
import com.utp.factory.spring_fecoma_api_rest.entities.Producto;
import com.utp.factory.spring_fecoma_api_rest.services.IFacturaService;
import com.utp.factory.spring_fecoma_api_rest.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api/v1/factura")
public class FacturaController {


    @Autowired
    private IFacturaService facturaService;

    @Autowired
    private IProductoService productoService;

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Factura show(@PathVariable Long id){
        return facturaService.findFacturaById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        facturaService.deleteFacturaById(id);
    }

    @GetMapping("/filtrar/{term}")
    @ResponseStatus(HttpStatus.OK)
    public List<Producto> filtrarProducto(@PathVariable String term){
        return productoService.findByNombreContainingIgnoreCase(term);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Factura crear(@RequestBody Factura factura){
        return facturaService.saveFactura(factura);
    }
}
