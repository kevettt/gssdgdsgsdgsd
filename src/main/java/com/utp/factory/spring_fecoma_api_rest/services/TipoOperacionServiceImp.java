package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.TipoOperacion;
import com.utp.factory.spring_fecoma_api_rest.repositories.TipoOperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoOperacionServiceImp implements ITipoOperacionService {

    @Autowired
    public TipoOperacionRepository tipoOperacionRepository;

    @Override
    public List<TipoOperacion> fineAll() {
        return (List<TipoOperacion>) tipoOperacionRepository.findAll();
    }

    @Override
    public Page<TipoOperacion> paginacion(Pageable pageable) {
        return tipoOperacionRepository.findAll(pageable);
    }

    @Override
    public TipoOperacion find(Long id) {
        return tipoOperacionRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        tipoOperacionRepository.deleteById(id);
    }

    @Override
    public TipoOperacion save(TipoOperacion tipoOperacion) {
        return tipoOperacionRepository.save(tipoOperacion);
    }

    @Override
    public TipoOperacion edit(TipoOperacion tipoOperacion) {
        return tipoOperacionRepository.save(tipoOperacion);
    }
}
