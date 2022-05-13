package com.utp.factory.spring_fecoma_api_rest.services;


import com.utp.factory.spring_fecoma_api_rest.entities.Puesto;
import com.utp.factory.spring_fecoma_api_rest.repositories.PuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuestoServiceImp implements IPuestoService{

    @Autowired
    private PuestoRepository puestoRepository;

    @Override
    public List<Puesto> fineAll() {
        return (List<Puesto>) puestoRepository.findAll();
    }

    @Override
    public Page<Puesto> paginacion(Pageable pageable) {
        return puestoRepository.findAll(pageable);
    }

    @Override
    public Puesto find(Long id) {
        return puestoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        puestoRepository.deleteById(id);
    }

    @Override
    public Puesto save(Puesto puesto) {
        return puestoRepository.save(puesto);
    }

    @Override
    public Puesto edit(Puesto puesto) {
        return puestoRepository.save(puesto);
    }
}
