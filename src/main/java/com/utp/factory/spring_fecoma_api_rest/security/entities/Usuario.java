package com.utp.factory.spring_fecoma_api_rest.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.utp.factory.spring_fecoma_api_rest.entities.Factura;
import com.utp.factory.spring_fecoma_api_rest.entities.Puesto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Entity(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String nombre;

    @NotEmpty
    @Column(nullable = false)
    private String apellido;

    @NotEmpty
    @Column(nullable = false)
    private String direccion;

    @NotEmpty
    @Column(nullable = false)
    @Size(max = 9,min = 8,message = "Ingrese entre 7 y 9 digitos")
    private String dni;

    @NotEmpty
    @Column(nullable = false)
    @Size(max = 9,min = 7,message = "Ingrese entre 7 y 9 digitos")
    private String telefono;

    @Column(nullable = false,unique = true)
    @NotEmpty
    @Email
    private String correo;

    @Column(unique = true, length = 20)
    private String username;

    @Column(length = 60)
    private String password;

    private Boolean enabled;

    @JsonIgnoreProperties(value = {"usuario","hibernateLazyInitializer","handler"},allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario",cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Usuario(){
        facturas = new ArrayList<>();
    }

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"usuarios","hibernateLazyInitializer","handler"})
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})})
    private Set<Role> roles =  new HashSet<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Puesto puesto;

    public Usuario(String nombre, String apellido, String direccion, String dni, String telefono, String correo, String username, String password, Boolean enabled) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
