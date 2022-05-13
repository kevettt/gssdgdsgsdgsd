package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Puesto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPuestoService {
    List<Puesto> fineAll();
    Page<Puesto> paginacion(Pageable pageable);//paginacion
    Puesto find(Long id);
    void eliminar(Long id);
    Puesto save(Puesto puesto);
    Puesto edit(Puesto puesto);
}
