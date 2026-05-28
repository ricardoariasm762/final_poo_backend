package com.universidad.torneo_system.controller;

import com.universidad.torneo_system.dto.TorneoMapper;
import com.universidad.torneo_system.dto.request.*;
import com.universidad.torneo_system.dto.response.TorneoResponse;
import com.universidad.torneo_system.model.enums.*;
import com.universidad.torneo_system.service.TorneoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/torneos")
public class TorneoController {

    private final TorneoService service;

    public TorneoController(TorneoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TorneoResponse> crear(
            @RequestBody @Valid TorneoRequest req) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(TorneoMapper.toResponse(
                service.crear(TorneoMapper.toEntity(req))));
    }

    @GetMapping
    public List<TorneoResponse> listar() {
        return service.listarTodos().stream()
            .map(TorneoMapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public TorneoResponse obtener(@PathVariable Long id) {
        return TorneoMapper.toResponse(service.buscarPorId(id));
    }

    @GetMapping("/deporte/{deporte}")
    public List<TorneoResponse> porDeporte(
            @PathVariable TipoDeporte deporte) {
        return service.listarPorDeporte(deporte).stream()
            .map(TorneoMapper::toResponse).toList();
    }

    @PutMapping("/{id}")
    public TorneoResponse actualizar(
            @PathVariable Long id,
            @RequestBody @Valid TorneoRequest req) {
        return TorneoMapper.toResponse(service.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/fase-grupos")
    public ResponseEntity<String> iniciarFaseGrupos(
            @PathVariable Long id,
            @RequestBody @Valid FaseGruposRequest req) {
        service.iniciarFaseGrupos(id, req.getGrupos());
        return ResponseEntity.ok("Fase de grupos iniciada correctamente");
    }

    @PostMapping("/{id}/reiniciar")
    public ResponseEntity<String> reiniciarTorneo(@PathVariable Long id) {
        service.reiniciarInscripciones(id);
        return ResponseEntity.ok("Torneo reiniciado a fase de inscripciones");
    }

    @PostMapping("/{id}/eliminacion")
    public ResponseEntity<String> iniciarEliminacion(@PathVariable Long id) {
        service.iniciarFaseEliminacion(id);
        return ResponseEntity.ok("Fase de eliminación iniciada correctamente");
    }

    @PostMapping("/{id}/avanzar-ronda")
    public ResponseEntity<String> avanzarRonda(
            @PathVariable Long id,
            @RequestParam String fase) {
        service.avanzarRonda(id, fase);
        return ResponseEntity.ok("Ronda avanzada correctamente");
    }
}