package com.utp.factory.spring_fecoma_api_rest.repositories;

import com.utp.factory.spring_fecoma_api_rest.security.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
