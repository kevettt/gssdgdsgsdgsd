package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Producto;
import com.utp.factory.spring_fecoma_api_rest.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/producto/")
public class ProductoController {
    @Autowired
    private IProductoService iProductoService;

    @GetMapping("/lista")//listar Empleados
    public ResponseEntity<List<Producto>> fineAll(){

        return ResponseEntity.ok(iProductoService.fineAll());
    }
    @GetMapping("/pagina/{page}")//paginacion
    public ResponseEntity<Page<Producto>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iProductoService.paginacion(PageRequest.of(page, 10)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long id){
        return  ResponseEntity.ok(iProductoService.find(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {

        return ResponseEntity.ok(iProductoService.save(producto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Producto> editEmpleado(@PathVariable Long id,@RequestBody Producto producto){
        Producto producto1 = iProductoService.find(id);
        producto1.setNombre(producto.getNombre());
        producto1.setDescripcion(producto.getDescripcion());
        producto1.setCategoria(producto.getCategoria());
        producto1.setCosto(producto.getCosto());
        producto1.setCantidad(producto.getCantidad());
        return ResponseEntity.ok(iProductoService.edit(producto1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        iProductoService.eliminar(id);
        return  new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
    }
}
