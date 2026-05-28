package com.universidad.torneo_system.dto.response;

import com.universidad.torneo_system.model.enums.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TorneoResponse {
    private Long id;
    private String nombre;
    private TipoDeporte deporte;
    private EstadoTorneo estado;
    private FormatoTorneo formato;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int cantidadGrupos;
    private int clasificadosPorGrupo;

    // Getters y Setters explícitos
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