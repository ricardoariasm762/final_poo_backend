package com.universidad.torneo_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universidad.torneo_system.model.Torneo;
import com.universidad.torneo_system.model.enums.EstadoTorneo;
import com.universidad.torneo_system.model.enums.TipoDeporte;

// TorneoRepository.java
@Repository
public interface TorneoRepository
        extends JpaRepository<Torneo, Long> {

    List<Torneo> findByDeporte(TipoDeporte deporte);
    List<Torneo> findByEstado(EstadoTorneo estado);
}
