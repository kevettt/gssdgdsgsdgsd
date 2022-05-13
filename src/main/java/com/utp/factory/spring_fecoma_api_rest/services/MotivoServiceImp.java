package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Motivo;
import com.utp.factory.spring_fecoma_api_rest.repositories.MotivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotivoServiceImp implements IMotivoService{

    @Autowired
    public MotivoRepository motivoRepository;

    @Override
    public List<Motivo> fineAll() {
        return (List<Motivo>) motivoRepository.findAll();
    }

    @Override
    public Page<Motivo> paginacion(Pageable pageable) {
        return motivoRepository.findAll(pageable);
    }

    @Override
    public Motivo find(Long id) {
        return motivoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        motivoRepository.deleteById(id);
    }

    @Override
    public Motivo save(Motivo motivo) {
        return motivoRepository.save(motivo);
    }

    @Override
    public Motivo edit(Motivo motivo) {
        return motivoRepository.save(motivo);
    }
}
