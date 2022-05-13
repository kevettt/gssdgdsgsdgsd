package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Kardex;
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
    public List<Kardex> fineAll() {
        return (List<Kardex>) kardexRepository.findAll();
    }

    @Override
    public Page<Kardex> paginacion(Pageable pageable) {
        return kardexRepository.findAll(pageable);
    }

    @Override
    public Kardex find(Long id) {
        return kardexRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        kardexRepository.deleteById(id);
    }

    @Override
    public Kardex save(Kardex kardex) {
        return kardexRepository.save(kardex);
    }

    @Override
    public Kardex edit(Kardex kardex) {
        return kardexRepository.save(kardex);
    }
}
