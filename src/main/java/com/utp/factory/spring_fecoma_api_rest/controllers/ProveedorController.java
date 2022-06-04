package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Proveedor;
import com.utp.factory.spring_fecoma_api_rest.services.IProveedorService;
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
@RequestMapping(value = "/api/v1/proveedor/")
@CrossOrigin
public class ProveedorController {

    @Autowired
    private IProveedorService iProveedorService;

    @GetMapping("/lista")//listar proveedores
    public ResponseEntity<List<Proveedor>> fineAll(){

        return ResponseEntity.ok(iProveedorService.fineAll());
    }

    @GetMapping("/pagina/{page}")//paginacion
    public ResponseEntity<Page<Proveedor >> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iProveedorService.paginacion(PageRequest.of(page, 4)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedor(@PathVariable Long id){
        return  ResponseEntity.ok(iProveedorService.find(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProveedor(@Valid @RequestBody Proveedor proveedor, BindingResult result) {

        Map<String,Object> response = new HashMap<String, Object>();

        if(result.hasErrors()) {
            List<String> errors= result.getFieldErrors()
                    .stream()
                    .map(er->er.getField()+" "+er.getDefaultMessage()).collect(Collectors.toList());
            response.put("error",errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

        }

        try {
            response.put("proveedor",iProveedorService.save(proveedor));
            response.put("mensaje","Se creo el proveedor con exito");
        }catch (DataAccessException e){
            response.put("mensaje","error al crear el proveedor");
            response.put("error",e.getMessage().concat(" = ").concat(e.getMostSpecificCause().getMessage()));
        }


        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Proveedor> editProveedor(@PathVariable Long id,@RequestBody Proveedor proveedor){
        Proveedor proveedor1 = iProveedorService.find(id);
        proveedor1.setNombre(proveedor.getNombre());
        proveedor1.setRuc(proveedor.getRuc());
        proveedor1.setDireccion(proveedor.getDireccion());
        return ResponseEntity.ok(iProveedorService.edit(proveedor1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable Long id){
        iProveedorService.eliminar(id);
        return  new ResponseEntity<>("Proveedor eliminado", HttpStatus.OK);
    }
}
