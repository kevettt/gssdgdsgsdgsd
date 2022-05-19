package com.utp.factory.spring_fecoma_api_rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "proveedores")
public class Proveedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tiene que ingresar un nombre")
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    @NotEmpty(message = "Tiene que ingresar el RUC")
    @Size(max = 11,min = 10,message = "Ingrese los 11 digitos del RUC")
    private String ruc;

    @Column(nullable = false)
    @NotEmpty(message = "Tiene que ingresar una dirección")
    private String direccion;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @OneToMany(fetch = FetchType.LAZY)
    private List<Representante> representantes;


    private static final long serialVersionUID=1L;
}
