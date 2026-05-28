package com.universidad.torneo_system.service;

import com.universidad.torneo_system.exception.RecursoNoEncontradoException;
import com.universidad.torneo_system.model.*;
import com.universidad.torneo_system.repository.TablaPosicionesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TablaService {

    private final TablaPosicionesRepository tablaRepo;

    public TablaService(TablaPosicionesRepository tablaRepo) {
        this.tablaRepo = tablaRepo;
    }

    // inicializa una fila en la tabla para cada participante
    @Transactional
    public void inicializarFila(Torneo torneo, Participante p, String grupo) {
        boolean yaExiste = tablaRepo
            .findByTorneoIdOrderByPuntosDesc(torneo.getId())
            .stream()
            .anyMatch(f -> f.getParticipanteId().equals(p.getId()));

        if (!yaExiste) {
            TablaPosiciones fila = new TablaPosiciones();
            fila.setTorneo(torneo);
            fila.setGrupo(grupo);
            fila.setParticipanteId(p.getId());
            fila.setParticipanteNombre(p.getNombre());
            fila.setPuntos(0);
            fila.setVictorias(0);
            fila.setEmpates(0);
            fila.setDerrotas(0);
            fila.setGolesFavor(0);
            fila.setGolesContra(0);
            
            tablaRepo.save(fila);
        }
    }

    @Transactional
    public void sumarPuntos(Torneo torneo, Participante participante,
                            int puntos, Resultado resultado, boolean esLocal) {
        TablaPosiciones fila = tablaRepo
            .findByTorneoIdOrderByPuntosDesc(torneo.getId())
            .stream()
            .filter(f -> f.getParticipanteId().equals(participante.getId()))
            .findFirst()
            .orElseThrow(() -> new RecursoNoEncontradoException(
                "Fila de tabla no encontrada para: " + participante.getNombre()));

        fila.setPuntos(fila.getPuntos() + puntos);

        int golesA   = esLocal
            ? (resultado.getPuntosLocal()     != null ? resultado.getPuntosLocal()     : 0)
            : (resultado.getPuntosVisitante() != null ? resultado.getPuntosVisitante() : 0);
        int golesEn  = esLocal
            ? (resultado.getPuntosVisitante() != null ? resultado.getPuntosVisitante() : 0)
            : (resultado.getPuntosLocal()     != null ? resultado.getPuntosLocal()     : 0);

        fila.setGolesFavor(fila.getGolesFavor()   + golesA);
        fila.setGolesContra(fila.getGolesContra() + golesEn);

        boolean gano = esLocal ? resultado.esVictoriaLocal() : resultado.esVictoriaVisitante();
        boolean perdio = esLocal ? resultado.esVictoriaVisitante() : resultado.esVictoriaLocal();
        boolean empate = resultado.esEmpate();

        if (gano) {
            fila.setVictorias(fila.getVictorias() + 1);
        } else if (empate) {
            fila.setEmpates(fila.getEmpates() + 1);
        } else if (perdio) {
            fila.setDerrotas(fila.getDerrotas() + 1);
        }

        tablaRepo.save(fila);
    }

    public List<TablaPosiciones> obtenerTablaPorTorneo(Long torneoId) {
        return tablaRepo.findByTorneoIdOrderByPuntosDesc(torneoId);
    }

    public List<TablaPosiciones> obtenerTablaPorGrupo(Long torneoId, String grupo) {
        return tablaRepo.findByTorneoIdAndGrupoOrderByPuntosDesc(torneoId, grupo);
    }
}