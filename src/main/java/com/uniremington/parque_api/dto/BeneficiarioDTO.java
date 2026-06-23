package com.uniremington.parque_api.dto;

import lombok.Data;

@Data
public class BeneficiarioDTO {
    private String nombre;
    private String documento;
    private int edad;
    private String genero;
    private String telefono;
    private String municipio;
    private String barrio;
    private String tipoPoblacion;
}