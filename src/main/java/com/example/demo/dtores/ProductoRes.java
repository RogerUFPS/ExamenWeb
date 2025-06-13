package com.example.demo.dtores;

import lombok.Data;

@Data
public class ProductoRes {
    private String referencia;
    private int cantidad;
    private Double precio;
    private Double descuento;
    private Double subtotal;
}
