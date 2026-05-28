package com.universidad.torneo_system.controller;

import com.universidad.torneo_system.dto.EncuentroMapper;
import com.universidad.torneo_system.dto.request.ResultadoRequest;
import com.universidad.torneo_system.dto.response.EncuentroResponse;
import com.universidad.torneo_system.model.Resultado;
import com.universidad.torneo_system.model.Encuentro;
import com.universidad.torneo_system.service.EncuentroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/encuentros")
public class EncuentroController {

    private final EncuentroService encuentroService;
    private final EncuentroMapper mapper;

    public EncuentroController(EncuentroService encuentroService, EncuentroMapper mapper) {
        this.encuentroService = encuentroService;
        this.mapper = mapper;
    }

    @GetMapping("/torneo/{torneoId}")
    public List<EncuentroResponse> porTorneo(@PathVariable Long torneoId) {
        List<Encuentro> encuentros = encuentroService.listarPorTorneo(torneoId);
        Map<Long, String> nombres = new HashMap<>();
        return encuentros.stream().map(e -> mapper.toResponse(e, nombres)).toList();
    }

    @GetMapping("/torneo/{torneoId}/fase/{fase}")
    public List<EncuentroResponse> porFase(
            @PathVariable Long torneoId,
            @PathVariable String fase) {
        List<Encuentro> encuentros = encuentroService.listarPorFase(torneoId, fase);
        Map<Long, String> nombres = new HashMap<>();
        return encuentros.stream().map(e -> mapper.toResponse(e, nombres)).toList();
    }

    @PutMapping("/{id}/resultado")
    public ResponseEntity<EncuentroResponse> registrarResultado(
            @PathVariable Long id,
            @RequestBody @Valid ResultadoRequest req) {

        Resultado resultado = new Resultado();
        resultado.setPuntosLocal(req.getPuntosLocal());
        resultado.setPuntosVisitante(req.getPuntosVisitante());
        resultado.setSetsLocal(req.getSetsLocal());
        resultado.setSetsVisitante(req.getSetsVisitante());

        EncuentroResponse response = mapper.toResponse(
                encuentroService.registrarResultado(id, resultado));

        return ResponseEntity.ok(response);
    }
}
