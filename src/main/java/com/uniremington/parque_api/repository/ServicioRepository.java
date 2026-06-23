package com.uniremington.parque_api.repository;

import com.uniremington.parque_api.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    @Query("SELECT s.facultad, COUNT(s) FROM Servicio s GROUP BY s.facultad")
    List<Object[]> obtenerConteoPorFacultad();

    @Query("SELECT s.estudiante.nombre, COUNT(s) FROM Servicio s GROUP BY s.estudiante.nombre")
    List<Object[]> contarServiciosPorEstudiante();

    @Query("SELECT s.tipoServicio, COUNT(s) FROM Servicio s GROUP BY s.tipoServicio")
    List<Object[]> contarPorTipoServicio();
    
    @Query("SELECT b.municipio, COUNT(s) FROM Servicio s JOIN s.beneficiario b GROUP BY b.municipio")
    List<Object[]> contarServiciosPorMunicipio();
}