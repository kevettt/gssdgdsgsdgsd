package com.utp.factory.spring_fecoma_api_rest.security.services;

import com.utp.factory.spring_fecoma_api_rest.security.entities.Usuario;

public interface IUsuarioService {
    Usuario findByUsername(String username);
}
