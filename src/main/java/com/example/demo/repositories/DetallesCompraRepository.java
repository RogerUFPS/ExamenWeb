package com.example.demo.repositories;

import com.example.demo.entities.DetallesCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallesCompraRepository extends JpaRepository<DetallesCompra, Integer> {
    List<DetallesCompra> findByCompraId(Integer compraId);
    List<DetallesCompra> findByProductoId(Integer productoId);
}