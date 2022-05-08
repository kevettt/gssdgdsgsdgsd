package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.entities.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmpleadoService {
    List<Empleado> fineAll();
    Page<Empleado> paginacion(Pageable pageable);//paginacion
    Empleado find(Long id);
    void eliminar(Long id);
    Empleado save(Empleado empleado);
    Empleado edit(Empleado empleado);
}
