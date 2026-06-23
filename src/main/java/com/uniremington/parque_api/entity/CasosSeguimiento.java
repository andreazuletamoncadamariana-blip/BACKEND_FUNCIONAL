package com.uniremington.parque_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class CasosSeguimiento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne @JoinColumn(name = "servicio_id")
    private Servicio servicio;
    
    private String estado;
    private LocalDate proximaFecha;
    private String avanceDescripcion;
}