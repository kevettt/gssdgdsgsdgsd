package com.utp.factory.spring_fecoma_api_rest.security.services;

import com.utp.factory.spring_fecoma_api_rest.security.entities.Role;
import com.utp.factory.spring_fecoma_api_rest.security.enums.RolNombre;
import com.utp.factory.spring_fecoma_api_rest.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolServiceImp implements IRolService{

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Role> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Role save(Role role) {
        return rolRepository.save(role);
    }

    @Override
    public Optional<Role> getByRol(RolNombre role) {
        return rolRepository.getByRol(role);
    }
}
