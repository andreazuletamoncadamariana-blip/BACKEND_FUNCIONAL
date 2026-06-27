package com.uniremington.parque_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "solicitudes_jornada")
@Data
public class SolicitudJornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entidad;
    private String responsable;
    private String telefono;
    private String correo;
    private String lugar;
    private Integer poblacion;

    @Column(length = 1000)
    private String servicios;

    @Column(length = 2000)
    private String observaciones;

}