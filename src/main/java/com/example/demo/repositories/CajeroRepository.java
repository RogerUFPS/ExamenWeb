package com.example.demo.repositories;

import com.example.demo.entities.Cajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CajeroRepository extends JpaRepository<Cajero, Integer> {
    Optional<Cajero> findByDocumento(String documento);
    Optional<Cajero> findByToken(String token);
}