package com.uniremington.parque_api.repository;

import com.uniremington.parque_api.entity.SolicitudJornada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudJornadaRepository extends JpaRepository<SolicitudJornada, Long> {

}