package com.uniremington.parque_api.repository;

import com.uniremington.parque_api.entity.CasosSeguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeguimientoRepository extends JpaRepository<CasosSeguimiento, Long> {

    @Query("""
        SELECT c.estado, COUNT(c)
        FROM CasosSeguimiento c
        GROUP BY c.estado
    """)
    List<Object[]> contarPorEstado();
}