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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "proveedores")
public class Proveedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = ": El campo nombre no puede ser vacio")
    @Column(nullable = false,unique = true)
    private String nombre;

    @Column(nullable = false,unique = true)
    @NotEmpty(message = ": El campo ruc no puede ser vacio")
    @Size(max = 11,min = 10,message = ": debe ingresar un ruc valido de 11 digitos")
    private String ruc;

    @Column(nullable = false,unique = true)
    @NotEmpty(message = ": El campo direccion no puede ser vacio")
    private String direccion;

    @JsonIgnoreProperties({"proveedor","hibernateLazyInitializer","handler"})
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "proveedor", cascade = CascadeType.ALL)
    private Representante representante;

    private static final long serialVersionUID=1L;
}
