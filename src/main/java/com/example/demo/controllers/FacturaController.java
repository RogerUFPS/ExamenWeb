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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtoreq.CajeroReq;
import com.example.demo.dtoreq.ClienteReq;
import com.example.demo.dtoreq.DetallesCompraReq;
import com.example.demo.dtoreq.FacturaReq;
import com.example.demo.dtoreq.PagoReq;
import com.example.demo.dtoreq.VendedorReq;
import com.example.demo.dtores.CompraRes;
import com.example.demo.dtores.FacturaRes;
import com.example.demo.service.FacturaService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class FacturaController {
    @Autowired
    private FacturaService factura;


    @PostMapping("/crear/{uuid}")
    public ResponseEntity<CompraRes> realizarCompra(@PathVariable String uuid, @RequestBody Double impuestos, @RequestBody ClienteReq cliente, @RequestBody List<DetallesCompraReq> productos, @RequestBody List<PagoReq> medios_pago, @RequestBody VendedorReq vendedor, @RequestBody CajeroReq cajero) {
        return ResponseEntity.ok(factura.realizarFactura(cliente, productos, medios_pago, vendedor, cajero, impuestos, uuid));
    }

    @PostMapping("/consultar/{uuid}")
    public ResponseEntity<FacturaRes> getFactura(@PathVariable String uuid, @RequestBody FacturaReq fr) {
        return ResponseEntity.ok(factura.getFactura(uuid, fr));
    }
}
