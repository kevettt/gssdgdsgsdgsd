package com.utp.factory.spring_fecoma_api_rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "representantes")
public class Representante implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tiene que ingresar un nombre")
    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    @NotEmpty(message = "Tiene que ingresar un apellido")
    private String apellidos;

    @Column(nullable = false)
    @Size(max = 9,min = 7,message = "Ingrese entre 7 y 9 digitos")
    private String telefono;

    @Column(nullable = false,unique = true)
    @NotEmpty
    @Email(message = "Ingrese email correcto")
    private String correo;

}
