package com.universidad.torneo_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universidad.torneo_system.model.Encuentro;
import com.universidad.torneo_system.model.enums.EstadoEncuentro;

// EncuentroRepository.java
@Repository
public interface EncuentroRepository
        extends JpaRepository<Encuentro, Long> {

    List<Encuentro> findByTorneoId(Long torneoId);
    List<Encuentro> findByTorneoIdAndFase(Long torneoId, String fase);
    List<Encuentro> findByEstado(EstadoEncuentro estado);
    void deleteByTorneoId(Long torneoId);
}
