package com.utp.factory.spring_fecoma_api_rest.repositories;

import com.utp.factory.spring_fecoma_api_rest.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
