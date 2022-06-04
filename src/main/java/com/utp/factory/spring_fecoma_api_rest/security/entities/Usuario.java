package com.utp.factory.spring_fecoma_api_rest.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.utp.factory.spring_fecoma_api_rest.entities.Puesto;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})})
    private List<Role> roles;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Puesto puesto;
}
