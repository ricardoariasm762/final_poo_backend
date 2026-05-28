package com.universidad.torneo_system.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TablaPosicionesResponse {
    private String grupo;
    private Long participanteId;
    private String participanteNombre;
    private int puntos;
    private int victorias;
    private int empates;
    private int derrotas;
    private int golesFavor;
    private int golesContra;
    private int diferenciaGoles;

    // Getters y Setters explícitos
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
    public int getDiferenciaGoles() { return diferenciaGoles; }
    public void setDiferenciaGoles(int diferenciaGoles) { this.diferenciaGoles = diferenciaGoles; }
}