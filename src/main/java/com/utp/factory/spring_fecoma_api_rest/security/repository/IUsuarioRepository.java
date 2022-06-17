package com.utp.factory.spring_fecoma_api_rest.security.repository;

import com.utp.factory.spring_fecoma_api_rest.entities.Factura;
import com.utp.factory.spring_fecoma_api_rest.security.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario,Long> {

    Usuario findByUsername(String username);
}
