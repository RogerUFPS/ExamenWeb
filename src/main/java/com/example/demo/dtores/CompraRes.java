package com.example.demo.dtores;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CompraRes {
    private int idNumero;
    private float total;
    private LocalDateTime fecha;
}
