package com.utp.factory.spring_fecoma_api_rest.repositories;

import com.utp.factory.spring_fecoma_api_rest.security.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByUsername(String username);
    boolean existsByCorreo(String correo);
    Optional<Usuario> findByUsername(String username);
}
