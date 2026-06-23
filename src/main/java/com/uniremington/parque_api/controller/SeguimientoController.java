package com.uniremington.parque_api.controller;

import com.uniremington.parque_api.entity.CasosSeguimiento;
import com.uniremington.parque_api.repository.SeguimientoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seguimiento")
@CrossOrigin("*")
public class SeguimientoController {

    private final SeguimientoRepository repo;

    public SeguimientoController(SeguimientoRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public CasosSeguimiento registrarSeguimiento(@RequestBody CasosSeguimiento caso) {
        return repo.save(caso);
    }
}