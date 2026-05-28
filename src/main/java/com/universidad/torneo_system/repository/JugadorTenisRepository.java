package com.universidad.torneo_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universidad.torneo_system.model.JugadorTenis;

// JugadorTenisRepository.java
@Repository
public interface JugadorTenisRepository
        extends JpaRepository<JugadorTenis, Long> {

    List<JugadorTenis> findByNombreContainingIgnoreCase(String nombre);
    List<JugadorTenis> findByNacionalidad(String nacionalidad);
}
