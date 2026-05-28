package com.universidad.torneo_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tabla_posiciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TablaPosiciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;

    // grupo al que pertenece: "A", "B", etc.
    private String grupo;

    // id del participante (equipo o jugador)
    @Column(nullable = false)
    private Long participanteId;

    // nombre guardado para mostrarlo sin resolver el tipo
    @Column(nullable = false)
    private String participanteNombre;

    private int puntos;
    private int victorias;
    private int empates;
    private int derrotas;
    private int golesFavor;
    private int golesContra;

    public int getDiferenciaGoles() {
        return golesFavor - golesContra;
    }

    // Getters y Setters explícitos para evitar errores de IDE sin Lombok
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Torneo getTorneo() { return torneo; }
    public void setTorneo(Torneo torneo) { this.torneo = torneo; }
    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
    public Long getParticipanteId() { return participanteId; }
    public void setParticipanteId(Long participanteId) { this.participanteId = participanteId; }
    public String getParticipanteNombre() { return participanteNombre; }
    public void setParticipanteNombre(String participanteNombre) { this.participanteNombre = participanteNombre; }
    public int getPuntos() { return puntos; }
    public void setPuntos(int puntos) { this.puntos = puntos; }
    public int getVictorias() { return victorias; }
    public void setVictorias(int victorias) { this.victorias = victorias; }
    public int getEmpates() { return empates; }
    public void setEmpates(int empates) { this.empates = empates; }
    public int getDerrotas() { return derrotas; }
    public void setDerrotas(int derrotas) { this.derrotas = derrotas; }
    public int getGolesFavor() { return golesFavor; }
    public void setGolesFavor(int golesFavor) { this.golesFavor = golesFavor; }
    public int getGolesContra() { return golesContra; }
    public void setGolesContra(int golesContra) { this.golesContra = golesContra; }
}