package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tienda")
@Data
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(nullable = false, length = 500)
    private String direccion;
    
    @Column(length = 50)
    private String uuid;
}
