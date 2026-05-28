package com.universidad.torneo_system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universidad.torneo_system.exception.EstadoInvalidoException;
import com.universidad.torneo_system.exception.RecursoNoEncontradoException;
import com.universidad.torneo_system.model.Encuentro;
import com.universidad.torneo_system.model.Participante;
import com.universidad.torneo_system.model.Resultado;
import com.universidad.torneo_system.model.Torneo;
import com.universidad.torneo_system.model.enums.EstadoEncuentro;
import com.universidad.torneo_system.repository.EncuentroRepository;
import com.universidad.torneo_system.repository.TorneoRepository;

import lombok.RequiredArgsConstructor;

@Service
public class EncuentroService {

    private final EncuentroRepository encuentroRepo;
    private final TorneoRepository torneoRepo;
    private final ParticipanteService participanteService;
    private final TablaService tablaService;

    public EncuentroService(EncuentroRepository encuentroRepo, TorneoRepository torneoRepo,
                            ParticipanteService participanteService, TablaService tablaService) {
        this.encuentroRepo = encuentroRepo;
        this.torneoRepo = torneoRepo;
        this.participanteService = participanteService;
        this.tablaService = tablaService;
    }

    public List<Encuentro> listarPorTorneo(Long torneoId) {
        return encuentroRepo.findByTorneoId(torneoId);
    }

    public List<Encuentro> listarPorFase(Long torneoId, String fase) {
        return encuentroRepo.findByTorneoIdAndFase(torneoId, fase);
    }

    // registra el resultado de un encuentro y actualiza la tabla
    @Transactional
    public Encuentro registrarResultado(Long encuentroId, Resultado resultado) {
        Encuentro encuentro = encuentroRepo.findById(encuentroId)
            .orElseThrow(() -> new RecursoNoEncontradoException("Encuentro no encontrado con id: " + encuentroId));

        if (encuentro.getEstado() == EstadoEncuentro.FINALIZADO)
            throw new EstadoInvalidoException("El encuentro con id " + encuentroId + " ya fue finalizado");

        encuentro.setResultado(resultado);
        encuentro.setEstado(EstadoEncuentro.FINALIZADO);
        encuentroRepo.save(encuentro);

        Torneo torneo = encuentro.getTorneo();

        // resolvemos los participantes concretos — polimorfismo aquí
        Participante local = participanteService
            .buscarPorId(encuentro.getLocalId(), torneo.getDeporte());
        Participante visitante = participanteService
            .buscarPorId(encuentro.getVisitanteId(), torneo.getDeporte());

        // calcularPuntos() se resuelve en runtime según el deporte
        int pLocal     = local.calcularPuntos(resultado, true);
        int pVisitante = visitante.calcularPuntos(resultado, false);

        // solo actualiza la tabla si es fase de grupos (no es CUARTOS, SEMI o FINAL)
        String fase = encuentro.getFase();
        boolean esEliminacion = List.of("CUARTOS", "SEMI", "FINAL").contains(fase);

        if (!esEliminacion) {
            tablaService.sumarPuntos(torneo, local,     pLocal,     resultado, true);
            tablaService.sumarPuntos(torneo, visitante, pVisitante, resultado, false);
        }

        return encuentro;
    }
}