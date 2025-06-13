package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtoreq.CajeroReq;
import com.example.demo.dtoreq.ClienteReq;
import com.example.demo.dtoreq.DetallesCompraReq;
import com.example.demo.dtoreq.FacturaReq;
import com.example.demo.dtoreq.PagoReq;
import com.example.demo.dtoreq.VendedorReq;
import com.example.demo.dtores.CajeroRes;
import com.example.demo.dtores.ClienteRes;
import com.example.demo.dtores.CompraRes;
import com.example.demo.dtores.FacturaRes;
import com.example.demo.dtores.ProductoRes;
import com.example.demo.entities.Cajero;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Compra;
import com.example.demo.entities.DetallesCompra;
import com.example.demo.entities.Pago;
import com.example.demo.entities.Producto;
import com.example.demo.entities.Tienda;
import com.example.demo.entities.TipoDocumento;
import com.example.demo.entities.Vendedor;
import com.example.demo.repositories.CajeroRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.CompraRepository;
import com.example.demo.repositories.DetallesCompraRepository;
import com.example.demo.repositories.PagoRepository;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.repositories.TiendaRepository;
import com.example.demo.repositories.TipoDocumentoRepository;
import com.example.demo.repositories.TipoPagoRepository;
import com.example.demo.repositories.VendedorRepository;

@Service
public class FacturaService {
    
    @Autowired
    private CajeroRepository cajero;
    @Autowired
    private ClienteRepository cliente;
    @Autowired
    private DetallesCompraRepository detalles;
    @Autowired
    private PagoRepository pago;
    @Autowired
    private VendedorRepository vendedor;
    @Autowired
    private TipoDocumentoRepository documento;
    @Autowired
    private CompraRepository compra;
    @Autowired
    private ProductoRepository producto;
    @Autowired
    private TipoPagoRepository tipoPago;
    @Autowired
    private TiendaRepository tienda;


    private CompraRes objToDtoCompra(Compra c) {
        CompraRes n = new CompraRes();
        n.setFecha(c.getFecha());
        n.setIdNumero(c.getId());
        n.setTotal(c.getTotal());
        return n;
    }

    public CompraRes realizarFactura(ClienteReq c, List<DetallesCompraReq> d, List<PagoReq> p, VendedorReq v, CajeroReq ca, Double impuestos, String idTienda) {
        
        Compra comp = new Compra();

        Optional<Cliente> conf = cliente.findByDocumento(c.getDocumento()); 
        Cliente cl;

        Tienda t = tienda.findByUuid(idTienda).orElseThrow(()-> new RuntimeException("La tienda no existe"));
        Vendedor ve = vendedor.findByDocumento(v.getDocumento()).orElseThrow(()-> new RuntimeException("El vendedor no existe"));

        Cajero caj = cajero.findByToken(ca.getToken()).orElseThrow(()-> new RuntimeException("El token no corresponde a ningun cajero"));
        if(!caj.getTienda().getNombre().equals(t.getNombre())) new RuntimeException("El cajero no esta asignado a esta tienda");


        Double valorReal = 0d;
        for(DetallesCompraReq det : d){
            Producto prod = producto.findByReferencia(det.getReferencia()).orElseThrow(()-> new RuntimeException("La referencia del producto " + det.getReferencia() + ", no existe, por favor revisar los datos"));
            if(det.getCantidad() > prod.getCantidad()) throw new RuntimeException("La cantidad a comprar supera el maximo del producto en tienda");
            valorReal+=prod.getPrecio()-(prod.getPrecio()*det.getDescuento());
        }

        valorReal+=valorReal*(impuestos/100);

        for(PagoReq pag : p) tipoPago.findByNombre(pag.getTipoPago()).orElseThrow(()-> new RuntimeException("El tipo de pago no esta permitido en la tienda")); 

        if(!conf.isPresent()) cl = registrarCliente(c);
        else cl = conf.get(); 

        comp.setCliente(cl);
        comp.setVendedor(ve);
        comp.setCajero(caj);
        comp.setTienda(caj.getTienda());
        comp.setImpuestos(impuestos);
        comp.setFecha(LocalDateTime.now());
        
        Double total = 0d;
        for(PagoReq r : p) {
            total += r.getValor();
        }

        if(valorReal != total) throw new RuntimeException("El valor de la factura no coincide con el valor real de pagos"); 

        comp.setTotal(total);
        compra.save(comp);
        return objToDtoCompra(comp);
    }

    public Cliente registrarCliente(ClienteReq c) {
        Cliente cl = new Cliente();
        cl.setNombre(c.getNombre());

        Optional<Cliente> tet = cliente.findByDocumento(cl.getDocumento());
        TipoDocumento tip = documento.findByNombre(c.getTipoDocumento()).orElseThrow(()-> new RuntimeException("El tipo de documento no existe"));

        if(tet.isPresent() && tet.get().getTipoDocumento().getNombre().equals(tip.getNombre())) throw new RuntimeException("Este documento ya existe, y lo tiene otra persona");

        cl.setDocumento(c.getDocumento());
        cl.setTipoDocumento(tip);
        return cl;
    }

    public FacturaRes getFactura(String idTienda, FacturaReq fac) {
        
        FacturaRes fcn = new FacturaRes();

        Cliente c = cliente.findByDocumento(fac.getClienteDocumento()).orElseThrow(()-> new RuntimeException("El cliente no existe"));
        Compra co = compra.findById(fac.getIdFactura()).orElseThrow(()-> new RuntimeException("La factura no existe"));
        List<DetallesCompra> deCo = detalles.findByCompra(co);

        if(deCo.isEmpty()) throw new RuntimeException("La compra no existe");

        CajeroRes cjn = new CajeroRes();
        cjn.setNombre(co.getCajero().getNombre());
        cjn.setDocumento(co.getCajero().getDocumento());
        ClienteRes cln = new ClienteRes();
        cln.setDocumento(c.getDocumento());
        cln.setNombre(c.getNombre());
        cln.setTipoDocumento(c.getTipoDocumento().getNombre());

        List<ProductoRes> prL = new ArrayList<>();

        for(DetallesCompra de : deCo) {
            ProductoRes tem = new ProductoRes();
            tem.setCantidad(de.getCantidad());
            tem.setPrecio(de.getPrecio());
            tem.setDescuento(de.getDescuento()*de.getPrecio());
            tem.setReferencia(de.getProducto().getReferencia());
            Double subtotal = (tem.getCantidad()*tem.getPrecio())-tem.getDescuento();
            tem.setSubtotal(subtotal);
            prL.add(tem);
        }
        fcn.setProductos(null);
        fcn.setCliente(cln);
        fcn.setCajero(cjn);
        fcn.setImpuestos(co.getTotal()*co.getImpuestos());
        fcn.setTotal(co.getTotal());
        fcn.setProductos(prL);
        return fcn;
    }
}
