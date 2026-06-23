package com.uniremington.parque_api.controller;

import org.springframework.web.bind.annotation.*;

import com.uniremington.parque_api.entity.Recurso;
import com.uniremington.parque_api.repository.RecursoRepository;

import java.util.List;

@RestController
@RequestMapping("/recursos")
@CrossOrigin("*")
public class RecursoController {

    private final RecursoRepository repo;

    public RecursoController(RecursoRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Recurso registrarAporte(@RequestBody Recurso recurso) {
        return repo.save(recurso);
    }

    @GetMapping
    public List<Recurso> listar() {
        return repo.findAll();
    }
}