package com.universidad.torneo_system.model;

import com.universidad.torneo_system.model.enums.EstadoEncuentro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "encuentros")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Encuentro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;

    // fase: "A", "B", "CUARTOS", "SEMI", "FINAL"
    @Column(nullable = false)
    private String fase;

    // ids de los participantes (resueltos con polimorfismo en el service)
    @Column(nullable = false)
    private Long localId;

    @Column(nullable = false)
    private Long visitanteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEncuentro estado;

    private LocalDateTime fechaProgramada;

    @Embedded
    private Resultado resultado;

    // Getters y Setters explícitos para evitar errores de IDE sin Lombok
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Torneo getTorneo() { return torneo; }
    public void setTorneo(Torneo torneo) { this.torneo = torneo; }
    public String getFase() { return fase; }
    public void setFase(String fase) { this.fase = fase; }
    public Long getLocalId() { return localId; }
    public void setLocalId(Long localId) { this.localId = localId; }
    public Long getVisitanteId() { return visitanteId; }
    public void setVisitanteId(Long visitanteId) { this.visitanteId = visitanteId; }
    public EstadoEncuentro getEstado() { return estado; }
    public void setEstado(EstadoEncuentro estado) { this.estado = estado; }
    public LocalDateTime getFechaProgramada() { return fechaProgramada; }
    public void setFechaProgramada(LocalDateTime fechaProgramada) { this.fechaProgramada = fechaProgramada; }
    public Resultado getResultado() { return resultado; }
    public void setResultado(Resultado resultado) { this.resultado = resultado; }
}