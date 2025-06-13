package com.example.demo.repositories;

import com.example.demo.entities.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Puedes agregar métodos personalizados aquí
    Optional<Cliente> findByDocumento(String documento);
}