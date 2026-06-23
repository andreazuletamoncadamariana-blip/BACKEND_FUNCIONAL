package com.uniremington.parque_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "recursos")
public class Recurso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String entidad;
    private String tipoAporte;
    private BigDecimal valor;
    private String descripcion;
}