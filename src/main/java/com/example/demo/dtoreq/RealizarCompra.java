package com.example.demo.dtoreq;

import java.util.List;

import lombok.Data;

@Data
public class RealizarCompra {
    private Double impuestos;
    private ClienteReq cliente;
    private List<DetallesCompraReq> productos;
    private List<PagoReq> medios_pago;
    private VendedorReq vendedor;
    private CajeroReq cajero;
}
