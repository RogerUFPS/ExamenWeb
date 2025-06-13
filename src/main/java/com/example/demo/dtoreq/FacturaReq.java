package com.example.demo.dtoreq;

import lombok.Data;

@Data
public class FacturaReq {
    private String token;
    private String clienteDocumento;
    private int idFactura;
}
