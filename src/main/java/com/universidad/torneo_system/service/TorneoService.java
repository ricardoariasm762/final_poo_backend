package com.universidad.torneo_system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universidad.torneo_system.exception.EstadoInvalidoException;
import com.universidad.torneo_system.exception.RecursoNoEncontradoException;
import com.universidad.torneo_system.model.Encuentro;
import com.universidad.torneo_system.model.Participante;
import com.universidad.torneo_system.model.Resultado;
import com.universidad.torneo_system.model.TablaPosiciones;
import com.universidad.torneo_system.model.Torneo;
import com.universidad.torneo_system.model.enums.EstadoEncuentro;
import com.universidad.torneo_system.model.enums.EstadoTorneo;
import com.universidad.torneo_system.model.enums.TipoDeporte;
import com.universidad.torneo_system.repository.EncuentroRepository;
import com.universidad.torneo_system.repository.TablaPosicionesRepository;
import com.universidad.torneo_system.repository.TorneoRepository;

import lombok.RequiredArgsConstructor;

@Service
public class TorneoService {

    private final TorneoRepository torneoRepo;
    private final EncuentroRepository encuentroRepo;
    private final TablaPosicionesRepository tablaRepo;
    private final ParticipanteService participanteService;
    private final TablaService tablaService;

    public TorneoService(TorneoRepository torneoRepo, EncuentroRepository encuentroRepo,
                         TablaPosicionesRepository tablaRepo, ParticipanteService participanteService,
                         TablaService tablaService) {
        this.torneoRepo = torneoRepo;
        this.encuentroRepo = encuentroRepo;
        this.tablaRepo = tablaRepo;
        this.participanteService = participanteService;
        this.tablaService = tablaService;
    }

    // ─────────────────────────────────────────────
    // CRUD básico
    // ─────────────────────────────────────────────

    public Torneo crear(Torneo torneo) {
        torneo.setEstado(EstadoTorneo.INSCRIPCIONES);
        return torneoRepo.save(torneo);
    }

    public Torneo buscarPorId(Long id) {
        return torneoRepo.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Torneo no encontrado con id: " + id));
    }

    public List<Torneo> listarTodos() {
        return torneoRepo.findAll();
    }

    public List<Torneo> listarPorDeporte(TipoDeporte deporte) {
        return torneoRepo.findByDeporte(deporte);
    }

    @Transactional
    public Torneo actualizar(Long id, com.universidad.torneo_system.dto.request.TorneoRequest req) {
        Torneo torneo = buscarPorId(id);
        
        // El nombre y las fechas se pueden editar siempre
        torneo.setNombre(req.getNombre());
        torneo.setFechaInicio(req.getFechaInicio());
        torneo.setFechaFin(req.getFechaFin());

        // El deporte, cantidad de grupos y clasificados solo se pueden editar en INSCRIPCIONES
        if (torneo.getEstado() == EstadoTorneo.INSCRIPCIONES) {
            torneo.setDeporte(req.getDeporte());
            torneo.setCantidadGrupos(req.getCantidadGrupos());
            torneo.setClasificadosPorGrupo(req.getClasificadosPorGrupo());
        }

        return torneoRepo.save(torneo);
    }

    @Transactional
    public void eliminar(Long id) {
        Torneo torneo = buscarPorId(id);
        if (torneo.getEstado() != EstadoTorneo.INSCRIPCIONES) {
            throw new EstadoInvalidoException("Solo se pueden eliminar torneos en estado INSCRIPCIONES");
        }
        
        // Eliminar tablas y encuentros asociados si existen (aunque en INSCRIPCIONES no deberían existir muchos)
        tablaRepo.deleteByTorneoId(id);
        encuentroRepo.deleteByTorneoId(id);
        torneoRepo.delete(torneo);
    }

    // ─────────────────────────────────────────────
    // FASE DE GRUPOS — genera fixtures automáticos
    // ─────────────────────────────────────────────

    @Transactional
    public void iniciarFaseGrupos(Long torneoId,
                                  Map<String, List<Long>> participantesPorGrupo) {
        Torneo torneo = buscarPorId(torneoId);

        if (torneo.getEstado() != EstadoTorneo.INSCRIPCIONES) {
            throw new EstadoInvalidoException("El torneo con ID " + torneoId + " debe estar en INSCRIPCIONES (actualmente: " + torneo.getEstado() + ")");
        }

        // inicializa la tabla y genera encuentros round-robin por grupo
        participantesPorGrupo.forEach((grupo, ids) -> {
            if (ids == null || ids.isEmpty()) {
                throw new EstadoInvalidoException("El grupo " + grupo + " no tiene participantes");
            }
            List<Participante> participantes = ids.stream()
                .map(id -> participanteService.buscarPorId(id, torneo.getDeporte()))
                .toList();

            // inicializa filas en la tabla
            participantes.forEach(p -> tablaService.inicializarFila(torneo, p, grupo));

            // genera todos vs todos dentro del grupo (round-robin)
            generarRoundRobin(torneo, participantes, grupo);
        });

        torneo.setEstado(EstadoTorneo.FASE_GRUPOS);
        torneoRepo.save(torneo);
    }

    @Transactional
    public void reiniciarInscripciones(Long torneoId) {
        Torneo torneo = buscarPorId(torneoId);
        
        // Eliminar tablas y encuentros asociados
        tablaRepo.deleteByTorneoId(torneoId);
        encuentroRepo.deleteByTorneoId(torneoId);
        
        torneo.setEstado(EstadoTorneo.INSCRIPCIONES);
        torneoRepo.save(torneo);
    }

    // algoritmo round-robin: cada participante juega contra todos los demás
    private void generarRoundRobin(Torneo torneo,
                                   List<Participante> participantes,
                                   String grupo) {
        int n = participantes.size();
        LocalDateTime fecha = torneo.getFechaInicio().atStartOfDay();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Participante local     = participantes.get(i);
                Participante visitante = participantes.get(j);

                Encuentro e = new Encuentro();
                e.setTorneo(torneo);
                e.setFase(grupo);
                e.setLocalId(local.getId());
                e.setVisitanteId(visitante.getId());
                e.setEstado(EstadoEncuentro.PROGRAMADO);
                e.setFechaProgramada(fecha);

                encuentroRepo.save(e);
                fecha = fecha.plusDays(2); // separa encuentros 2 días
            }
        }
    }

    // ─────────────────────────────────────────────
    // FASE DE ELIMINACIÓN — toma clasificados
    // ─────────────────────────────────────────────

    @Transactional
    public void iniciarFaseEliminacion(Long torneoId) {
        Torneo torneo = buscarPorId(torneoId);

        if (torneo.getEstado() != EstadoTorneo.FASE_GRUPOS) {
            throw new EstadoInvalidoException("El torneo debe estar en FASE_GRUPOS para iniciar eliminación");
        }

        // verifica que todos los encuentros de grupos estén finalizados
        boolean pendientes = encuentroRepo.findByTorneoId(torneoId).stream()
            .filter(e -> !e.getFase().equals("CUARTOS")
                       && !e.getFase().equals("SEMI")
                       && !e.getFase().equals("FINAL"))
            .anyMatch(e -> e.getEstado() != EstadoEncuentro.FINALIZADO);

        if (pendientes) {
            throw new EstadoInvalidoException(
                "Aún hay encuentros de fase de grupos sin finalizar");
        }

        // obtiene clasificados: los N primeros de cada grupo
        List<Long> clasificados = obtenerClasificados(torneo);

        if (clasificados.isEmpty()) {
            throw new EstadoInvalidoException("No se encontraron clasificados en la tabla de posiciones");
        }

        // genera el cuadro de eliminación
        generarCuadroEliminacion(torneo, clasificados);

        torneo.setEstado(EstadoTorneo.ELIMINACION);
        torneoRepo.save(torneo);
    }

    private List<Long> obtenerClasificados(Torneo torneo) {
        int clasificadosPorGrupo = torneo.getClasificadosPorGrupo();
        List<Long> clasificados = new ArrayList<>();

        // obtiene los grupos únicos registrados en el torneo
        List<String> grupos = tablaRepo
            .findByTorneoIdOrderByPuntosDesc(torneo.getId())
            .stream()
            .map(TablaPosiciones::getGrupo)
            .distinct()
            .toList();

        if (grupos.isEmpty()) {
            throw new EstadoInvalidoException("No hay grupos registrados para el torneo " + torneo.getId());
        }

        for (String grupo : grupos) {
            List<Long> clasificadosGrupo = tablaRepo
                .findByTorneoIdAndGrupoOrderByPuntosDesc(torneo.getId(), grupo)
                .stream()
                .limit(clasificadosPorGrupo)
                .map(TablaPosiciones::getParticipanteId)
                .toList();
            
            if (clasificadosGrupo.size() < clasificadosPorGrupo) {
                // opcional: advertir o lanzar error si no hay suficientes participantes para clasificar
            }
            
            clasificados.addAll(clasificadosGrupo);
        }

        return clasificados;
    }

    private void generarCuadroEliminacion(Torneo torneo, List<Long> clasificados) {
        if (clasificados.size() < 2) {
            throw new EstadoInvalidoException("Se necesitan al menos 2 clasificados para generar el cuadro");
        }

        LocalDateTime fecha = LocalDateTime.now().plusDays(3);
        String fase = determinarFase(clasificados.size());

        // empareja: 1ro vs último, 2do vs penúltimo
        int n = clasificados.size();
        for (int i = 0; i < n / 2; i++) {
            Encuentro e = new Encuentro();
            e.setTorneo(torneo);
            e.setFase(fase);
            e.setLocalId(clasificados.get(i));
            e.setVisitanteId(clasificados.get(n - 1 - i));
            e.setEstado(EstadoEncuentro.PROGRAMADO);
            e.setFechaProgramada(fecha);

            encuentroRepo.save(e);
            fecha = fecha.plusDays(3);
        }
    }

    private String determinarFase(int cantidad) {
        if (cantidad >= 8) return "CUARTOS";
        if (cantidad >= 4) return "SEMI";
        return "FINAL";
    }

    // avanza la eliminación a la siguiente ronda con los ganadores
    @Transactional
    public void avanzarRonda(Long torneoId, String faseActual) {
        Torneo torneo = buscarPorId(torneoId);

        List<Encuentro> encuentros = encuentroRepo
            .findByTorneoIdAndFase(torneoId, faseActual);

        if (encuentros.isEmpty()) {
            throw new EstadoInvalidoException("No hay encuentros registrados para la fase: " + faseActual);
        }

        boolean todosFinalizados = encuentros.stream()
            .allMatch(e -> e.getEstado() == EstadoEncuentro.FINALIZADO);

        if (!todosFinalizados) {
            throw new EstadoInvalidoException("Hay encuentros sin finalizar en la fase: " + faseActual);
        }

        // recoge ganadores de cada encuentro
        List<Long> ganadores = encuentros.stream()
            .map(e -> {
                Resultado r = e.getResultado();
                if (r == null) {
                    throw new EstadoInvalidoException("El encuentro " + e.getId() + " no tiene resultado registrado");
                }
                if (r.esEmpate() && (torneo.getDeporte() == TipoDeporte.BALONCESTO || torneo.getDeporte() == TipoDeporte.TENIS)) {
                    throw new EstadoInvalidoException("El deporte " + torneo.getDeporte() + " no permite empates en eliminación");
                }
                return r.esVictoriaLocal() ? e.getLocalId() : e.getVisitanteId();
            })
            .toList();

        if (ganadores.size() == 1) {
            // esto ocurre si solo se envió un encuentro (una final)
            torneo.setEstado(EstadoTorneo.FINALIZADO);
            torneoRepo.save(torneo);
            return;
        }

        String siguienteFase = determinarFase(ganadores.size());
        
        // si la fase actual era la final y ya hay un ganador, el torneo termina
        if (faseActual.equals("FINAL")) {
            torneo.setEstado(EstadoTorneo.FINALIZADO);
            torneoRepo.save(torneo);
            return;
        }

        generarCuadroEliminacion(torneo, ganadores);
    }
}