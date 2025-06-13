package com.example.demo.repositories;

import com.example.demo.entities.Vendedor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
    Optional<Vendedor> findByDocumento(String documento);
}