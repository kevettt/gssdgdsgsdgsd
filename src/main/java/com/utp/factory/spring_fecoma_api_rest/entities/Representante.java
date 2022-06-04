package com.utp.factory.spring_fecoma_api_rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @NotEmpty(message = ": El campo nombre no puede ser vacio")
    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    @NotEmpty(message = ": El campo apellido no puede ser vacio")
    private String apellidos;

    @Column(nullable = false)
    @NotEmpty(message = ": El campo telefono no puede ser vacio")
    @Size(max = 9,min = 7,message = ": Ingrese un telefono valido de entre 7 y 9 digitos")
    private String telefono;

    @Column(nullable = false,unique = true)
    @NotEmpty(message = ": El campo correo no puede ser vacio")
    @Email(message = ": Ingrese un email correcto")
    private String correo;

    @JsonIgnoreProperties({"representante","hibernateLazyInitializer","handler"})
    @OneToOne(fetch = FetchType.LAZY)
    private Proveedor proveedor;

}
