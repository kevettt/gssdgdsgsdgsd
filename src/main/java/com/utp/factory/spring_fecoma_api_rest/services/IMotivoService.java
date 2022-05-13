package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Categoria;
import com.utp.factory.spring_fecoma_api_rest.entities.Motivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMotivoService {
    List<Motivo> fineAll();
    Page<Motivo> paginacion(Pageable pageable);
    Motivo find(Long id);
    void eliminar(Long id);
    Motivo save(Motivo motivo);
    Motivo edit(Motivo motivo);
}
