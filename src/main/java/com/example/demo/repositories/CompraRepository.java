package com.example.demo.repositories;

import com.example.demo.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
    List<Compra> findByClienteId(Integer clienteId);
    List<Compra> findByTiendaId(Integer tiendaId);
    List<Compra> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}