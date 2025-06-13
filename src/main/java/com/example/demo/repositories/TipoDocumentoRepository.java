package com.example.demo.repositories;


import com.example.demo.entities.TipoDocumento;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {
    Optional<TipoDocumento> findByNombre(String nombre);
}