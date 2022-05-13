package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoriaService {
    List<Categoria> fineAll();
    Page<Categoria> paginacion(Pageable pageable);
    Categoria find(Long id);
    void eliminar(Long id);
    Categoria save(Categoria categoria);
    Categoria edit(Categoria categoria);

}
