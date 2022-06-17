package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Producto;
import com.utp.factory.spring_fecoma_api_rest.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImp implements IProductoService{
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> fineAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public Page<Producto> paginacion(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    public Producto find(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);

    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto edit(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public List<Producto> findByNombreContainingIgnoreCase(String term) {
        return productoRepository.findByNombreContainingIgnoreCase(term);
    }
}
