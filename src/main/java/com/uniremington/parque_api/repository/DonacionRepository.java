package com.uniremington.parque_api.repository;

import com.uniremington.parque_api.entity.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonacionRepository
        extends JpaRepository<Donacion, Long> {
}
