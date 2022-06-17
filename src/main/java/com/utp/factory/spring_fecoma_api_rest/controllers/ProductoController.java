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
@CrossOrigin
public class ProductoController {
    @Autowired
    private IProductoService iProductoService;

    @GetMapping("/lista")//listar Empleados
    public ResponseEntity<List<Producto>> fineAll(){

        return ResponseEntity.ok(iProductoService.fineAll());
    }
    @GetMapping("/pagina/{page}")//paginacion
    public ResponseEntity<Page<Producto>> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iProductoService.paginacion(PageRequest.of(page, 4)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProducto(@PathVariable Long id){
        Map<String,Object> response = new HashMap<String, Object>();

        try {
            Producto producto = iProductoService.find(id);
            response.put("producto",producto);

        }catch (Exception e){
            response.put("mensaje","error al buscar el producto");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMessage()));
        }
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto, BindingResult result) {
        Producto producto1 = new Producto();
        Map<String,Object> response = new HashMap<String, Object>();
        if(result.hasErrors()) {
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

        }
        try {
            producto1 = iProductoService.save(producto);
            response.put("producto",producto1);
            response.put("mensaje","Se creo el producto con exito");
        }catch (DataAccessException e){
            response.put("mensaje","error al crear el producto");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMostSpecificCause().getMessage()));
        }



        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editEmpleado(@Valid @RequestBody Producto producto, BindingResult result,@PathVariable Long id){
        Producto productoactual = new Producto();
        Producto productoactualizado= new Producto();
        productoactual = iProductoService.find(id);
        Map<String,Object> response = new HashMap<String, Object>();

        if(result.hasErrors()) {
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

        }
        if (productoactual==null){
            response.put("mensaje","el producto con el id: ".concat(id.toString().concat("no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try {
            productoactual.setNombre(producto.getNombre());
            productoactual.setDescripcion(producto.getDescripcion());
            productoactual.setCategoria(producto.getCategoria());
            productoactual.setCosto(producto.getCosto());
            productoactual.setPrecio(producto.getPrecio());
            productoactual.setCantidad(producto.getCantidad());
            productoactual.setProveedor(producto.getProveedor());
            productoactualizado=iProductoService.edit(productoactual);
            response.put("producto",productoactualizado);
            response.put("mensaje","se actualizo el producto correctamente");

        }catch (DataAccessException e){
            response.put("mensaje","error al actualizar el producto");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        Map<String,Object> response = new HashMap<String, Object>();
        try {
            response.put("mensaje","Producto elimnado");
            iProductoService.eliminar(id);

        }catch (Exception e){
            response.put("mensaje","error al eliminar el producto");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMessage()));
        }

        return  new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
    }
}
