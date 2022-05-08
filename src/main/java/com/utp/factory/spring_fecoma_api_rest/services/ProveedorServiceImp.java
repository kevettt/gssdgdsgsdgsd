package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Proveedor;
import com.utp.factory.spring_fecoma_api_rest.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImp implements IProveedorService{

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> fineAll() {

        return (List<Proveedor>) proveedorRepository.findAll();
    }

    @Override
    public Page<Proveedor> paginacion(Pageable pageable) {//paginacion
        return proveedorRepository.findAll(pageable);
    }

    @Override
    public Proveedor find(Long id) {

        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        proveedorRepository.deleteById(id);

    }

    @Override
    public Proveedor save(Proveedor proveedor) {

        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor edit(Proveedor proveedor) {

        return proveedorRepository.save(proveedor);
    }
}
