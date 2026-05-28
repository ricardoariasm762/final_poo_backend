package com.universidad.torneo_system.dto;

import com.universidad.torneo_system.dto.request.TorneoRequest;
import com.universidad.torneo_system.dto.response.*;
import com.universidad.torneo_system.model.*;
import com.universidad.torneo_system.model.enums.FormatoTorneo;

public class TorneoMapper {

    public static Torneo toEntity(TorneoRequest req) {
        Torneo t = new Torneo();
        t.setNombre(req.getNombre());
        t.setDeporte(req.getDeporte());
        t.setFechaInicio(req.getFechaInicio());
        t.setFechaFin(req.getFechaFin());
        t.setCantidadGrupos(req.getCantidadGrupos());
        t.setClasificadosPorGrupo(req.getClasificadosPorGrupo());
        t.setFormato(FormatoTorneo.FASE_GRUPOS_Y_ELIMINACION);
        return t;
    }

    public static TorneoResponse toResponse(Torneo t) {
        TorneoResponse res = new TorneoResponse();
        res.setId(t.getId());
        res.setNombre(t.getNombre());
        res.setDeporte(t.getDeporte());
        res.setEstado(t.getEstado());
        res.setFormato(t.getFormato());
        res.setFechaInicio(t.getFechaInicio());
        res.setFechaFin(t.getFechaFin());
        res.setCantidadGrupos(t.getCantidadGrupos());
        res.setClasificadosPorGrupo(t.getClasificadosPorGrupo());
        return res;
    }
}