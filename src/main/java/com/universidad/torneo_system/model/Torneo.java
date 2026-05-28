package com.universidad.torneo_system.model;

import java.time.LocalDate;

import com.universidad.torneo_system.model.enums.EstadoTorneo;
import com.universidad.torneo_system.model.enums.FormatoTorneo;
import com.universidad.torneo_system.model.enums.TipoDeporte;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "torneos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDeporte deporte;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTorneo estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormatoTorneo formato;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private int cantidadGrupos;

    // cuántos clasifican de cada grupo a eliminación
    private int clasificadosPorGrupo;

    // Getters y Setters explícitos para evitar errores de IDE sin Lombok
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public TipoDeporte getDeporte() { return deporte; }
    public void setDeporte(TipoDeporte deporte) { this.deporte = deporte; }
    public EstadoTorneo getEstado() { return estado; }
    public void setEstado(EstadoTorneo estado) { this.estado = estado; }
    public FormatoTorneo getFormato() { return formato; }
    public void setFormato(FormatoTorneo formato) { this.formato = formato; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public int getCantidadGrupos() { return cantidadGrupos; }
    public void setCantidadGrupos(int cantidadGrupos) { this.cantidadGrupos = cantidadGrupos; }
    public int getClasificadosPorGrupo() { return clasificadosPorGrupo; }
    public void setClasificadosPorGrupo(int clasificadosPorGrupo) { this.clasificadosPorGrupo = clasificadosPorGrupo; }
}