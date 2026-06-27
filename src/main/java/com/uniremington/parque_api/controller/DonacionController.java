package com.uniremington.parque_api.controller;

import com.uniremington.parque_api.entity.Donacion;
import com.uniremington.parque_api.repository.DonacionRepository;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/donaciones")
@CrossOrigin("*")
public class DonacionController {

    private final DonacionRepository repository;

    public DonacionController(DonacionRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Donacion registrar(@RequestBody Donacion donacion) {

        donacion.setFechaRegistro(LocalDateTime.now());

        return repository.save(donacion);
    }

    @GetMapping
    public List<Donacion> listar() {
        return repository.findAll();
    }
}