package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "producto")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(length = 1000)
    private String descripcion;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private Double precio;
    
    @ManyToOne
    @JoinColumn(name = "tipo_producto_id", nullable = false)
    private TipoProducto tipoProducto;
    
    @Column(columnDefinition = "integer default 0")
    private Integer cantidad;
    
    @Column(length = 20)
    private String referencia;
}