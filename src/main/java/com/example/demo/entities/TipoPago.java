package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_pago")
@Data
public class TipoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String nombre;
}
