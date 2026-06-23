package com.uniremington.parque_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniremington.parque_api.entity.Rol;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}