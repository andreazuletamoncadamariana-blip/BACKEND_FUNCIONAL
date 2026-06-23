package com.uniremington.parque_api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "servicios")
@Data
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "beneficiario_id")
    private Beneficiario beneficiario;

    @ManyToOne @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne @JoinColumn(name = "profesor_id")
    private Usuario profesor; // Referencia al profesor supervisor

    private String tipoServicio;
    private String facultad;
    private String descripcion;
    private int tiempoHorasEstimadas;
    private String resultado;
    private String observaciones;
    private LocalDateTime fecha = LocalDateTime.now();
}