package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Kardex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IKardexService {
    List<Kardex> fineAll();
    Page<Kardex> paginacion(Pageable pageable);
    Kardex find(Long id);
    void eliminar(Long id);
    Kardex save(Kardex kardex);
    Kardex edit(Kardex kardex);
}
