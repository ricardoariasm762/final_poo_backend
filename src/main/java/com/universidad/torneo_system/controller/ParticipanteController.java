package com.universidad.torneo_system.controller;

import com.universidad.torneo_system.dto.request.*;
import com.universidad.torneo_system.model.*;
import com.universidad.torneo_system.service.ParticipanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {

    private final ParticipanteService service;

    public ParticipanteController(ParticipanteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Participante> listarTodos() {
        return service.listarTodos();
    }

    // ── Fútbol ──────────────────────────────────
    @PostMapping("/futbol")
    public ResponseEntity<EquipoFutbol> crearFutbol(
            @RequestBody @Valid EquipoFutbolRequest req) {
        EquipoFutbol equipo = new EquipoFutbol();
        equipo.setNombre(req.getNombre());
        equipo.setCiudad(req.getCiudad());
        equipo.setEntrenador(req.getEntrenador());
        equipo.setEstadio(req.getEstadio());
        equipo.setColorCamiseta(req.getColorCamiseta());
        equipo.setNumeroPlantel(req.getNumeroPlantel());
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.crearEquipoFutbol(equipo));
    }

    @GetMapping("/futbol")
    public List<EquipoFutbol> listarFutbol() {
        return service.listarEquiposFutbol();
    }

    // ── Baloncesto ──────────────────────────────
    @PostMapping("/baloncesto")
    public ResponseEntity<EquipoBaloncesto> crearBaloncesto(
            @RequestBody @Valid EquipoBaloncestoRequest req) {
        EquipoBaloncesto equipo = new EquipoBaloncesto();
        equipo.setNombre(req.getNombre());
        equipo.setCiudad(req.getCiudad());
        equipo.setEntrenador(req.getEntrenador());
        equipo.setCancha(req.getCancha());
        equipo.setConferencia(req.getConferencia());
        equipo.setNumeroPlantel(req.getNumeroPlantel());

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.crearEquipoBaloncesto(equipo));
    }

    @GetMapping("/baloncesto")
    public List<EquipoBaloncesto> listarBaloncesto() {
        return service.listarEquiposBaloncesto();
    }

    // ── Tenis ───────────────────────────────────
    @PostMapping("/tenis")
    public ResponseEntity<JugadorTenis> crearTenis(
            @RequestBody @Valid JugadorTenisRequest req) {
        JugadorTenis jugador = new JugadorTenis();
        jugador.setNombre(req.getNombre());
        jugador.setCiudad(req.getCiudad());
        jugador.setEntrenador(req.getEntrenador());
        jugador.setNacionalidad(req.getNacionalidad());
        jugador.setRanking(req.getRanking());
        jugador.setManoHabil(req.getManoHabil());

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.crearJugadorTenis(jugador));
    }

    @GetMapping("/tenis")
    public List<JugadorTenis> listarTenis() {
        return service.listarJugadoresTenis();
    }
}