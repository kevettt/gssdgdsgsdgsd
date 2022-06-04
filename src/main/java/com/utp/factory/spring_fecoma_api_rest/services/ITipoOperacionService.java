package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.TipoOperacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITipoOperacionService {
    List<TipoOperacion> fineAll();
    Page<TipoOperacion> paginacion(Pageable pageable);
    TipoOperacion find(Long id);
    void eliminar(Long id);
    TipoOperacion save(TipoOperacion tipoOperacion);
    TipoOperacion edit(TipoOperacion tipoOperacion);
}
