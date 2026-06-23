package com.uniremington.parque_api.controller;

import com.uniremington.parque_api.entity.Servicio;
import com.uniremington.parque_api.service.ServicioService;
import com.uniremington.parque_api.dto.ServicioRequest; // Importación necesaria
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/servicios")
@CrossOrigin("*")
public class ServicioController {

    private final ServicioService service;

    public ServicioController(ServicioService service) {
        this.service = service;
    }

    @PostMapping
    public Servicio crear(@RequestBody ServicioRequest s) {
        return service.crear(s);
    }

    @GetMapping
    public List<Servicio> listar() {
        return service.listar();
    }

    @GetMapping("/dashboard-stats")
    public Map<String, Object> obtenerEstadisticasJornada() {
        return service.obtenerEstadisticasJornada();
    }
}