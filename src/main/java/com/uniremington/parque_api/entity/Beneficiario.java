package com.uniremington.parque_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "beneficiarios")
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String documento;

    private int edad;
    private String genero;
    private String telefono;
    private String municipio;
    private String barrio;

    @Column(name = "tipo_poblacion")
    private String tipoPoblacion;

    @Column(name = "autorizacion_datos")
    private Boolean autorizacionDatos;
}