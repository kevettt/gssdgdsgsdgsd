package com.utp.factory.spring_fecoma_api_rest.security.services;

import com.utp.factory.spring_fecoma_api_rest.security.entities.Role;
import com.utp.factory.spring_fecoma_api_rest.security.enums.RolNombre;

import java.util.List;
import java.util.Optional;

public interface IRolService {

    List<Role> findAll();
    Role save(Role role);

    Optional<Role> getByRol(RolNombre role);
}
