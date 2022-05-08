package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProveedorService {
    List<Proveedor> fineAll();
    Page<Proveedor> paginacion(Pageable pageable);//paginacion
    Proveedor find(Long id);
    void eliminar(Long id);
    Proveedor save(Proveedor proveedor);
    Proveedor edit(Proveedor proveedor);
}
