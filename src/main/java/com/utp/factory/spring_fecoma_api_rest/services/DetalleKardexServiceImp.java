package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.DetalleKardex;
import com.utp.factory.spring_fecoma_api_rest.repositories.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleKardexServiceImp implements IDetalleKardexService {

    @Autowired
    private DetalleRepository detalleRepository;
    @Override
    public List<DetalleKardex> fineAll() {
        return (List<DetalleKardex>) detalleRepository.findAll();
    }

    @Override
    public Page<DetalleKardex> paginacion(Pageable pageable) {
        return detalleRepository.findAll(pageable);
    }

    @Override
    public DetalleKardex find(Long id) {
        return detalleRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        detalleRepository.deleteById(id);
    }

    @Override
    public DetalleKardex save(DetalleKardex kardex) {
        return detalleRepository.save(kardex);
    }

    @Override
    public DetalleKardex edit(DetalleKardex kardex) {
        return detalleRepository.save(kardex);
    }
}
