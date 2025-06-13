package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "compra")
@Data
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "tienda_id", nullable = false)
    private Tienda tienda;
    
    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;
    
    @ManyToOne
    @JoinColumn(name = "cajero_id", nullable = false)
    private Cajero cajero;
    
    @Column(columnDefinition = "numeric(10,2) default 0")
    private Double total;
    
    @Column(columnDefinition = "numeric(5,2) default 0")
    private Double impuestos;
    
    @Column(columnDefinition = "timestamp default now()")
    private LocalDateTime fecha;
    
    @Column(length = 1000)
    private String observaciones;
}
