package com.example.demo.dtoreq;

import lombok.Data;

@Data
public class PagoReq {
    
    private String tipoPago;
    private String tipoTarjeta;
    private Double valor;
    private int cuotas;
}
