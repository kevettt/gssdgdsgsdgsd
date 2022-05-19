package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Producto;
import com.utp.factory.spring_fecoma_api_rest.services.IProductoService;
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
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto, BindingResult result) {
        Producto producto1 = new Producto();
        var response = new HashMap<String, Object>();
        if(result.hasErrors()) {
            var errors= result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

        }
        try {
            producto1 = iProductoService.save(producto);
        }catch (DataAccessException e){
            response.put("mensaje","error al crear el producto");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMostSpecificCause().getMessage()));
        }
        response.put("producto",producto1);
        response.put("mensaje","Se creo el producto con exito");


        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
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
