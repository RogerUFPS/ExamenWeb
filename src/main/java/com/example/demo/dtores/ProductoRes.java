package com.example.demo.dtores;

import lombok.Data;

@Data
public class ProductoRes {
    private String referencia;
    private int cantidad;
    private float precio;
    private float descuento;
    private float subtotal;
}
