package com.uniremington.parque_api.dto;

import lombok.Data;

@Data
public class ServicioRequest {
    private Long beneficiarioId;
    private Long estudianteId;
    private Long profesorId;
    private String tipoServicio;
    private String facultad;
    private String descripcion;
    private Integer tiempoHorasEstimadas; 
    private String resultado;
    private String observaciones;
}