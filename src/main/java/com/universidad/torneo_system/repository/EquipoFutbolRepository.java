package com.universidad.torneo_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universidad.torneo_system.model.EquipoFutbol;

@Repository
public interface EquipoFutbolRepository
        extends JpaRepository<EquipoFutbol, Long> {

    List<EquipoFutbol> findByNombreContainingIgnoreCase(String nombre);
}