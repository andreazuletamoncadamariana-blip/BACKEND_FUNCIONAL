package com.uniremington.parque_api.controller;

import com.uniremington.parque_api.entity.Beneficiario;
import com.uniremington.parque_api.repository.BeneficiarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sync")
@CrossOrigin("*")
public class SyncController {

    private final BeneficiarioRepository repo;

    public SyncController(BeneficiarioRepository repo) {
        this.repo = repo;
    }

    // SINCRONIZAR BENEFICIARIOS OFFLINE
    @PostMapping("/beneficiarios")
    public List<Beneficiario> syncBeneficiarios(@RequestBody List<Beneficiario> lista) {

        for (Beneficiario b : lista) {

            repo.findByDocumento(b.getDocumento())
                    .ifPresentOrElse(
                            existing -> {
                                // actualizar si existe
                                existing.setNombre(b.getNombre());
                                existing.setMunicipio(b.getMunicipio());
                                repo.save(existing);
                            },
                            () -> repo.save(b)
                    );
        }

        return repo.findAll();
    }
}