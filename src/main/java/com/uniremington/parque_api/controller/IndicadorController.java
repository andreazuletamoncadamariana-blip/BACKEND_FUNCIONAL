package com.uniremington.parque_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniremington.parque_api.service.BeneficiarioService;

@RestController
@RequestMapping("/indicadores")
public class IndicadorController {

    private final BeneficiarioService service;

    public IndicadorController(BeneficiarioService service) {
        this.service = service;
    }

    @GetMapping("/total-beneficiarios")
    public long total() {
        return service.listar().size();
    }
}