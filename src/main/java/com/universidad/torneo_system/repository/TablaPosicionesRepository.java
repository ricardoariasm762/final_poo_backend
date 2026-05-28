package com.universidad.torneo_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universidad.torneo_system.model.TablaPosiciones;

// TablaPosicionesRepository.java
@Repository
public interface TablaPosicionesRepository
        extends JpaRepository<TablaPosiciones, Long> {

    List<TablaPosiciones> findByTorneoIdOrderByPuntosDesc(Long torneoId);
    List<TablaPosiciones> findByTorneoIdAndGrupoOrderByPuntosDesc(
            Long torneoId, String grupo);
    void deleteByTorneoId(Long torneoId);
}
