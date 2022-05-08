package com.utp.factory.spring_fecoma_api_rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "proveedores")
public class Proveedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;

    @JsonIgnoreProperties({"hibernateLazyInitiazer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_representante")
    private Representante representante;
}
