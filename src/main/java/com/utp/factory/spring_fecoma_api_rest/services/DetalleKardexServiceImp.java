package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.DetalleKardex;
import com.utp.factory.spring_fecoma_api_rest.repositories.KardexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KardexServiceImp implements IKardexService{

    @Autowired
    private KardexRepository kardexRepository;
    @Override
    public List<DetalleKardex> fineAll() {
        return (List<DetalleKardex>) kardexRepository.findAll();
    }

    @Override
    public Page<DetalleKardex> paginacion(Pageable pageable) {
        return kardexRepository.findAll(pageable);
    }

    @Override
    public DetalleKardex find(Long id) {
        return kardexRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        kardexRepository.deleteById(id);
    }

    @Override
    public DetalleKardex save(DetalleKardex kardex) {
        return kardexRepository.save(kardex);
    }

    @Override
    public DetalleKardex edit(DetalleKardex kardex) {
        return kardexRepository.save(kardex);
    }
}
