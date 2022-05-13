package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductoService {
    List<Producto> fineAll();
    Page<Producto> paginacion(Pageable pageable);//paginacion
    Producto find(Long id);
    void eliminar(Long id);
    Producto save(Producto producto);
    Producto edit(Producto producto);
}
