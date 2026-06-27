package com.uniremington.parque_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "donaciones")
@Data
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    private String correo;

    private Double monto;

    @Column(name = "metodo_pago")
    private String metodoPago;

    private String mensaje;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}