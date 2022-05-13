package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Empleado;
import com.utp.factory.spring_fecoma_api_rest.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImp implements IEmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> fineAll() {

        return (List<Empleado>) empleadoRepository.findAll();
    }

    @Override
    public Page<Empleado> paginacion(Pageable pageable) {

        return empleadoRepository.findAll(pageable);
    }

    @Override
    public Empleado find(Long id) {

        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);

    }

    @Override
    public Empleado save(Empleado empleado) {

        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado edit(Empleado empleado) {

        return empleadoRepository.save(empleado);
    }
}
