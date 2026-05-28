package com.universidad.torneo_system.dto.response;

import com.universidad.torneo_system.model.enums.EstadoEncuentro;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncuentroResponse {
    private Long id;
    private Long torneoId;
    private String nombreTorneo;
    private String fase;
    private Long localId;
    private String nombreLocal;
    private Long visitanteId;
    private String nombreVisitante;
    private EstadoEncuentro estado;
    private LocalDateTime fechaProgramada;
    private Integer puntosLocal;
    private Integer puntosVisitante;
    private Integer setsLocal;
    private Integer setsVisitante;

    // Getters y Setters explícitos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTorneoId() { return torneoId; }
    public void setTorneoId(Long torneoId) { this.torneoId = torneoId; }
    public String getNombreTorneo() { return nombreTorneo; }
    public void setNombreTorneo(String nombreTorneo) { this.nombreTorneo = nombreTorneo; }
    public String getFase() { return fase; }
    public void setFase(String fase) { this.fase = fase; }
    public Long getLocalId() { return localId; }
    public void setLocalId(Long localId) { this.localId = localId; }
    public String getNombreLocal() { return nombreLocal; }
    public void setNombreLocal(String nombreLocal) { this.nombreLocal = nombreLocal; }
    public Long getVisitanteId() { return visitanteId; }
    public void setVisitanteId(Long visitanteId) { this.visitanteId = visitanteId; }
    public String getNombreVisitante() { return nombreVisitante; }
    public void setNombreVisitante(String nombreVisitante) { this.nombreVisitante = nombreVisitante; }
    public EstadoEncuentro getEstado() { return estado; }
    public void setEstado(EstadoEncuentro estado) { this.estado = estado; }
    public LocalDateTime getFechaProgramada() { return fechaProgramada; }
    public void setFechaProgramada(LocalDateTime fechaProgramada) { this.fechaProgramada = fechaProgramada; }
    public Integer getPuntosLocal() { return puntosLocal; }
    public void setPuntosLocal(Integer puntosLocal) { this.puntosLocal = puntosLocal; }
    public Integer getPuntosVisitante() { return puntosVisitante; }
    public void setPuntosVisitante(Integer puntosVisitante) { this.puntosVisitante = puntosVisitante; }
    public Integer getSetsLocal() { return setsLocal; }
    public void setSetsLocal(Integer setsLocal) { this.setsLocal = setsLocal; }
    public Integer getSetsVisitante() { return setsVisitante; }
    public void setSetsVisitante(Integer setsVisitante) { this.setsVisitante = setsVisitante; }
}