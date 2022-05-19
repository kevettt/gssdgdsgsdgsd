package com.utp.factory.spring_fecoma_api_rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "detalles")
public class DetalleKardex implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 6000,min = 0,message = "Ingrese un numero superior a 0 digitos")
    @Min(value = 0)
    private double monto;

    @Column(nullable = false)
    @Size(max = 6000,min = 0,message = "Ingrese un numero superior a 0 digitos")
    @Min(value = 0)
    private int cantidad;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Kardex kardex;

    public double getMontoItem(){
        monto=cantidad*producto.getCosto();
        return monto;
    }

}
