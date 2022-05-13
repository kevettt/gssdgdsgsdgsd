package com.utp.factory.spring_fecoma_api_rest.services;


import com.utp.factory.spring_fecoma_api_rest.entities.Representante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRepresentanteService {
    List<Representante> fineAll();
    Page<Representante> paginacion(Pageable pageable);//paginacion
    Representante find(Long id);
    void eliminar(Long id);
    Representante save(Representante representante);
    Representante edit(Representante representante);
}
