package com.uniremington.parque_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.uniremington.parque_api.entity.Estudiante;
import com.uniremington.parque_api.repository.EstudianteRepository;

@RestController
@RequestMapping("/estudiantes")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PutMapping("/{id}")
    public Estudiante actualizar(
            @PathVariable Long id,
            @RequestBody Estudiante estudiante) {

        estudiante.setId(id);
        return repo.save(estudiante);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}