package com.example.demo.dtores;


import java.util.List;

import lombok.Data;

@Data
public class FacturaRes {
    private Double total;
    private Double impuestos;
    private ClienteRes cliente;
    private List<ProductoRes> productos;
    private CajeroRes cajero;
}   
