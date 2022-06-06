package com.utp.factory.spring_fecoma_api_rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "puestos")
public class Puesto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Tiene que ingresar el nombre del puesto")
    private String puesto;

    @Column(nullable = false)
    @Min(value = 0)
    private double sueldo;

    @Column(nullable = false)
    @NotEmpty(message = "Tiene que ingresar descripción del puesto")
    private String descripcion;
}
