package com.utp.factory.spring_fecoma_api_rest.security.entities;

import com.utp.factory.spring_fecoma_api_rest.security.enums.RolNombre;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@Entity(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNombre rol;
}
