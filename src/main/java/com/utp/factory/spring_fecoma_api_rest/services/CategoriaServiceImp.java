package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Categoria;
import com.utp.factory.spring_fecoma_api_rest.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImp implements ICategoriaService{
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> fineAll() {

        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Override
    public Page<Categoria> paginacion(Pageable pageable) {

        return categoriaRepository.findAll(pageable);
    }

    @Override
    public Categoria find(Long id) {

        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {

        categoriaRepository.deleteById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria edit(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }
}
