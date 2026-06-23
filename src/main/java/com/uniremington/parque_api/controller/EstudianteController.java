package com.uniremington.parque_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.uniremington.parque_api.entity.Estudiante;
import com.uniremington.parque_api.repository.EstudianteRepository;

@RestController
@RequestMapping("/estudiantes")
@CrossOrigin("*")
public class EstudianteController {

    private final EstudianteRepository repo;

    public EstudianteController(EstudianteRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Estudiante> listar() { 
        return repo.findAll(); 
    }
    
    @PostMapping
    public Estudiante registrar(@RequestBody Estudiante estudiante) { 
        return repo.save(estudiante); 
    }
}