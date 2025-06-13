package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pago")
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;
    
    @ManyToOne
    @JoinColumn(name = "tipo_pago_id", nullable = false)
    private TipoPago tipoPago;
    
    @Column(length = 20)
    private String tarjetaTipo;
    
    @Column
    private float valor;
    
    private Integer cuotas;
}