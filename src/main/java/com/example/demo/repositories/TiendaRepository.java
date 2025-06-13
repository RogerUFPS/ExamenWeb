package com.example.demo.repositories;

import com.example.demo.entities.Tienda;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Integer> {
    Optional<Tienda> findByUuid(String uuid);
}