package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Representante;
import com.utp.factory.spring_fecoma_api_rest.repositories.RepresentanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentanteServiceImp implements IRepresentanteService{
    @Autowired
    private RepresentanteRepository representanteRepository;

    @Override
    public List<Representante> fineAll() {
        return (List<Representante>) representanteRepository.findAll();
    }

    @Override
    public Page<Representante> paginacion(Pageable pageable) {
        return representanteRepository.findAll(pageable);
    }

    @Override
    public Representante find(Long id) {
        return representanteRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        representanteRepository.deleteById(id);
    }

    @Override
    public Representante save(Representante representante) {
        return representanteRepository.save(representante);
    }

    @Override
    public Representante edit(Representante representante) {
        return representanteRepository.save(representante);
    }
}
