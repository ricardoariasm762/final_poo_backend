package com.universidad.torneo_system.dto;

import com.universidad.torneo_system.dto.response.TablaPosicionesResponse;
import com.universidad.torneo_system.model.TablaPosiciones;

public class TablaMapper {

    public static TablaPosicionesResponse toResponse(TablaPosiciones t) {
        TablaPosicionesResponse res = new TablaPosicionesResponse();
        res.setGrupo(t.getGrupo());
        res.setParticipanteId(t.getParticipanteId());
        res.setParticipanteNombre(t.getParticipanteNombre());
        res.setPuntos(t.getPuntos());
        res.setVictorias(t.getVictorias());
        res.setEmpates(t.getEmpates());
        res.setDerrotas(t.getDerrotas());
        res.setGolesFavor(t.getGolesFavor());
        res.setGolesContra(t.getGolesContra());
        res.setDiferenciaGoles(t.getDiferenciaGoles());
        return res;
    }
}