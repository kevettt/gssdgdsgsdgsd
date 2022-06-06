package com.utp.factory.spring_fecoma_api_rest.security.repository;

import com.utp.factory.spring_fecoma_api_rest.security.entities.Role;
import com.utp.factory.spring_fecoma_api_rest.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Role,Long> {

    Optional<Role> getByRol(RolNombre role);
}
