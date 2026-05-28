package com.universidad.torneo_system.dto;

import com.universidad.torneo_system.dto.response.EncuentroResponse;
import com.universidad.torneo_system.model.*;
import com.universidad.torneo_system.model.enums.TipoDeporte;
import com.universidad.torneo_system.service.ParticipanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EncuentroMapper {

    private final ParticipanteService participanteService;

    public EncuentroMapper(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    public EncuentroResponse toResponse(Encuentro e) {
        return toResponse(e, null);
    }

    public EncuentroResponse toResponse(Encuentro e, Map<Long, String> nombresParticipantes) {
        TipoDeporte deporte = e.getTorneo().getDeporte();

        String nombreLocal = resolverNombre(e.getLocalId(), deporte, nombresParticipantes);
        String nombreVisitante = resolverNombre(e.getVisitanteId(), deporte, nombresParticipantes);

        Resultado r = e.getResultado();

        EncuentroResponse res = new EncuentroResponse();
        res.setId(e.getId());
        res.setTorneoId(e.getTorneo().getId());
        res.setNombreTorneo(e.getTorneo().getNombre());
        res.setFase(e.getFase());
        res.setLocalId(e.getLocalId());
        res.setNombreLocal(nombreLocal);
        res.setVisitanteId(e.getVisitanteId());
        res.setNombreVisitante(nombreVisitante);
        res.setEstado(e.getEstado());
        res.setFechaProgramada(e.getFechaProgramada());
        res.setPuntosLocal(r != null ? r.getPuntosLocal() : null);
        res.setPuntosVisitante(r != null ? r.getPuntosVisitante() : null);
        res.setSetsLocal(r != null ? r.getSetsLocal() : null);
        res.setSetsVisitante(r != null ? r.getSetsVisitante() : null);

        return res;
    }

    private String resolverNombre(Long id, TipoDeporte deporte, Map<Long, String> cache) {
        if (cache == null) {
            return participanteService.buscarPorId(id, deporte).getNombre();
        }
        return cache.computeIfAbsent(id, k -> participanteService.buscarPorId(k, deporte).getNombre());
    }
}
