package com.uniremington.parque_api.repository;

import com.uniremington.parque_api.entity.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
    Optional<Beneficiario> findByDocumento(String documento);
}