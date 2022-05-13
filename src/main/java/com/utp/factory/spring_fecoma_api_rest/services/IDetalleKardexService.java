package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.DetalleKardex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IKardexService {
    List<DetalleKardex> fineAll();
    Page<DetalleKardex> paginacion(Pageable pageable);
    DetalleKardex find(Long id);
    void eliminar(Long id);
    DetalleKardex save(DetalleKardex categoria);
    DetalleKardex edit(DetalleKardex categoria);
}
