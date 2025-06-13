package com.example.demo.repositories;

import com.example.demo.entities.Compra;
import com.example.demo.entities.DetallesCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetallesCompraRepository extends JpaRepository<DetallesCompra, Integer> {
    List<DetallesCompra> findByCompra(Compra compra);
}