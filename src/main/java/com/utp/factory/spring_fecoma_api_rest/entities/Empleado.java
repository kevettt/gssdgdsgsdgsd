package com.utp.factory.spring_fecoma_api_rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "empleados")
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tiene que ingresar un nombre")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "Tiene que ingresar un apellido")
    @Column(nullable = false)
    private String apellido;

    @NotEmpty(message = "Tiene que ingresar una dirreción")
    @Column(nullable = false)
    private String direccion;

    @NotEmpty(message = "Tiene que ingresar un documento de identidad")
    @Column(nullable = false)
    @Size(max = 9,min = 8,message = "Ingrese entre 7 y 9 digitos")
    private String dni;

    @NotEmpty(message = "Tiene que ingresar un numero telefonico")
    @Column(nullable = false)
    @Size(max = 9,min = 7,message = "Ingrese entre 7 y 9 digitos")
    private String telefono;

    @Column(nullable = false,unique = true)
    @NotEmpty
    @Email(message = "Ingrese email correcto")
    private String correo;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Puesto puesto;
}
