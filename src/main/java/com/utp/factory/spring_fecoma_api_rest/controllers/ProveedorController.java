package com.utp.factory.spring_fecoma_api_rest.controllers;

import com.utp.factory.spring_fecoma_api_rest.entities.Proveedor;
import com.utp.factory.spring_fecoma_api_rest.services.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/proveedor/")
public class ProveedorController {

    @Autowired
    private IProveedorService iProveedorService;

    @GetMapping("/lista")//listar proveedores
    public ResponseEntity<List<Proveedor > > fineAll(){

        return ResponseEntity.ok(iProveedorService.fineAll());
    }

    @GetMapping("/pagina/{page}")//paginacion
    public ResponseEntity<Page<Proveedor >> fineAll(@PathVariable Integer page){
        return ResponseEntity.ok(iProveedorService.paginacion(PageRequest.of(page, 10)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedor(@PathVariable Long id){
        return  ResponseEntity.ok(iProveedorService.find(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {

        return ResponseEntity.ok(iProveedorService.save(proveedor));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Proveedor> editProveedor(@PathVariable Long id,@RequestBody Proveedor proveedor){
        Proveedor proveedor1 = iProveedorService.find(id);
        proveedor1.setNombre(proveedor.getNombre());
        proveedor1.setCorreo(proveedor.getCorreo());
        proveedor1.setTelefono(proveedor.getTelefono());
        return ResponseEntity.ok(iProveedorService.edit(proveedor1));

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable Long id){
        iProveedorService.eliminar(id);
        return  new ResponseEntity<>("Proveedor eliminado", HttpStatus.OK);
    }
}
