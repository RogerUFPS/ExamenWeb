package com.example.demo.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtoreq.CajeroReq;
import com.example.demo.dtoreq.ClienteReq;
import com.example.demo.dtoreq.DetallesCompraReq;
import com.example.demo.dtoreq.FacturaReq;
import com.example.demo.dtoreq.PagoReq;
import com.example.demo.dtoreq.VendedorReq;
import com.example.demo.dtores.CompraRes;
import com.example.demo.dtores.FacturaRes;
import com.example.demo.entities.DetallesCompra;
import com.example.demo.service.FacturaService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class FacturaController {
    @Autowired
    private FacturaService factura;

    @PostMapping("/crear/{id}")
    public ResponseEntity<CompraRes> realizarCompra(@PathVariable String uuid, @RequestBody ClienteReq cR, @RequestBody List<DetallesCompraReq> dC, @RequestBody List<PagoReq> pR, @RequestBody VendedorReq vR, @RequestBody CajeroReq cRe, @RequestBody Double impuestos) {
        return ResponseEntity.ok(factura.realizarFactura(cR, dC, pR, vR, cRe, impuestos, uuid));
    }

    @PostMapping("/consultar/{id}")
    public ResponseEntity<FacturaRes> getFactura(@PathVariable String uuid, @RequestBody FacturaReq fr) {
        return ResponseEntity.ok(factura.getFactura(uuid, fr));
    }
}
