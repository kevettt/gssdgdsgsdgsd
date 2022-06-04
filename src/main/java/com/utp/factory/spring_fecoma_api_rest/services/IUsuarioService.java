package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.security.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> fineAll();
    Page<Usuario> paginacion(Pageable pageable);//paginacion
    Usuario find(Long id);
    void eliminar(Long id);
    Usuario save(Usuario usuario);
    Usuario edit(Usuario usuario);
}
