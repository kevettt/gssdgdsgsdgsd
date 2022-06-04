package com.utp.factory.spring_fecoma_api_rest.security.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String rol;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;
}
