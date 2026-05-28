package com.universidad.torneo_system.controller;

import com.universidad.torneo_system.dto.TablaMapper;
import com.universidad.torneo_system.dto.response.TablaPosicionesResponse;
import com.universidad.torneo_system.service.TablaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tabla")
public class TablaController {

    private final TablaService tablaService;

    public TablaController(TablaService tablaService) {
        this.tablaService = tablaService;
    }

    @GetMapping("/torneo/{torneoId}")
    public List<TablaPosicionesResponse> porTorneo(
            @PathVariable Long torneoId) {
        return tablaService.obtenerTablaPorTorneo(torneoId)
            .stream().map(TablaMapper::toResponse).toList();
    }

    @GetMapping("/torneo/{torneoId}/grupo/{grupo}")
    public List<TablaPosicionesResponse> porGrupo(
            @PathVariable Long torneoId,
            @PathVariable String grupo) {
        return tablaService.obtenerTablaPorGrupo(torneoId, grupo)
            .stream().map(TablaMapper::toResponse).toList();
    }
}