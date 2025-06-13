package com.example.demo.dtoreq;

import lombok.Data;

@Data
public class DetallesCompraReq {
    private String referencia;
    private int cantidad;
    private float descuento;
}
