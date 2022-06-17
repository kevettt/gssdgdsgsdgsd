package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Factura;
import com.utp.factory.spring_fecoma_api_rest.entities.Producto;
import com.utp.factory.spring_fecoma_api_rest.services.IFacturaService;
import com.utp.factory.spring_fecoma_api_rest.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> filtrarProducto(@PathVariable String term){
        Map<String,Object> respuesta = new HashMap<>();
        if(term.length()>0){
            try {
                respuesta.put("data",productoService.findByNombreContainingIgnoreCase(term));
                respuesta.put("mensaje","respuesta exitosa");
                return new ResponseEntity<>(respuesta,HttpStatus.OK);
            }catch (Exception e){
                respuesta.put("mensaje","error en la consulta");
                respuesta.put("error",e.getMessage().concat(" = ").concat(e.getMessage()));
            }
        }
        return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Factura crear(@RequestBody Factura factura){
        return facturaService.saveFactura(factura);
    }
}
