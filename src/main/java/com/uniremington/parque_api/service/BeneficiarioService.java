package com.uniremington.parque_api.service;

import com.uniremington.parque_api.entity.Beneficiario;
import com.uniremington.parque_api.repository.BeneficiarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BeneficiarioService {

    
    private final BeneficiarioRepository repo;

    public BeneficiarioService(BeneficiarioRepository repo) {
        this.repo = repo;
    }

    public Beneficiario crear(Beneficiario b) {
        return repo.save(b);
    }

    public List<Beneficiario> listar() {
        return repo.findAll();
    }

    public Beneficiario buscar(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Beneficiario actualizar(Long id, Beneficiario b) {
        Beneficiario old = buscar(id);
        old.setNombre(b.getNombre());
        old.setMunicipio(b.getMunicipio());
        return repo.save(old);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}