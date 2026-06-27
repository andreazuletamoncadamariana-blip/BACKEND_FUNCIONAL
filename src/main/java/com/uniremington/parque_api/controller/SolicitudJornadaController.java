package com.uniremington.parque_api.controller;

import com.uniremington.parque_api.entity.SolicitudJornada;
import com.uniremington.parque_api.repository.SolicitudJornadaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jornadas")
@CrossOrigin(origins = "http://localhost:4200")
public class SolicitudJornadaController {

    private final SolicitudJornadaRepository jornadaRepository;

    SolicitudJornadaController(SolicitudJornadaRepository jornadaRepository) {
        this.jornadaRepository = jornadaRepository;
    }

    @GetMapping
    public List<SolicitudJornada> listar() {
        return jornadaRepository.findAll();
    }

    @PostMapping
    public SolicitudJornada guardar(
            @RequestBody SolicitudJornada jornada) {

        return jornadaRepository.save(jornada);
    }
}