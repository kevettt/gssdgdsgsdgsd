package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoriaService {
    List<Categoria> fineAll();
    Page<Categoria> pagi
}
