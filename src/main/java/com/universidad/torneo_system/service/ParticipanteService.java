package com.universidad.torneo_system.service;

import org.springframework.stereotype.Service;

import com.universidad.torneo_system.exception.RecursoNoEncontradoException;
import com.universidad.torneo_system.model.EquipoBaloncesto;
import com.universidad.torneo_system.model.EquipoFutbol;
import com.universidad.torneo_system.model.JugadorTenis;
import com.universidad.torneo_system.model.Participante;
import com.universidad.torneo_system.model.enums.TipoDeporte;
import com.universidad.torneo_system.repository.EquipoBaloncestoRepository;
import com.universidad.torneo_system.repository.EquipoFutbolRepository;
import com.universidad.torneo_system.repository.JugadorTenisRepository;

import lombok.RequiredArgsConstructor;

@Service
public class ParticipanteService {

    private final EquipoFutbolRepository futbolRepo;
    private final EquipoBaloncestoRepository baloncestoRepo;
    private final JugadorTenisRepository tenisRepo;

    public ParticipanteService(EquipoFutbolRepository futbolRepo,
                               EquipoBaloncestoRepository baloncestoRepo,
                               JugadorTenisRepository tenisRepo) {
        this.futbolRepo = futbolRepo;
        this.baloncestoRepo = baloncestoRepo;
        this.tenisRepo = tenisRepo;
    }

    // devuelve la interfaz — polimorfismo desde aquí
    public Participante buscarPorId(Long id, TipoDeporte deporte) {
        return switch (deporte) {
            case FUTBOL      -> futbolRepo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Equipo fútbol no encontrado con id: " + id));

            case BALONCESTO  -> baloncestoRepo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Equipo baloncesto no encontrado con id: " + id));

            case TENIS       -> tenisRepo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Jugador tenis no encontrado con id: " + id));

        };
    }

    public EquipoFutbol crearEquipoFutbol(EquipoFutbol equipo) {
        return futbolRepo.save(equipo);
    }

    public EquipoBaloncesto crearEquipoBaloncesto(EquipoBaloncesto equipo) {
        return baloncestoRepo.save(equipo);
    }

    public JugadorTenis crearJugadorTenis(JugadorTenis jugador) {
        return tenisRepo.save(jugador);
    }

    public java.util.List<EquipoFutbol> listarEquiposFutbol() {
        return futbolRepo.findAll();
    }

    public java.util.List<EquipoBaloncesto> listarEquiposBaloncesto() {
        return baloncestoRepo.findAll();
    }

    public java.util.List<JugadorTenis> listarJugadoresTenis() {
        return tenisRepo.findAll();
    }

    public java.util.List<Participante> listarTodos() {
        java.util.List<Participante> todos = new java.util.ArrayList<>();
        todos.addAll(futbolRepo.findAll());
        todos.addAll(baloncestoRepo.findAll());
        todos.addAll(tenisRepo.findAll());
        return todos;
    }
}