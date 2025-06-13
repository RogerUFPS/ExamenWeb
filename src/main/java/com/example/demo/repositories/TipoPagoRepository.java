package com.example.demo.repositories;

import com.example.demo.entities.TipoPago;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Integer> {
    Optional<TipoPago> findByNombre(String nombre);
}