package com.example.demo.repositories;

import com.example.demo.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findByCompraId(Integer compraId);
    List<Pago> findByTipoPagoId(Integer tipoPagoId);
}