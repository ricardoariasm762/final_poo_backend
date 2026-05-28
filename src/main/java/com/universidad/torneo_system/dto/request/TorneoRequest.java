package com.universidad.torneo_system.dto.request;

import java.time.LocalDate;

import com.universidad.torneo_system.model.enums.TipoDeporte;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TorneoRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El deporte es obligatorio")
    private TipoDeporte deporte;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Min(value = 2, message = "Mínimo 2 grupos")
    private int cantidadGrupos;

    @Min(value = 1, message = "Mínimo 1 clasificado por grupo")
    private int clasificadosPorGrupo;

    // Getters y Setters explícitos
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public TipoDeporte getDeporte() { return deporte; }
    public void setDeporte(TipoDeporte deporte) { this.deporte = deporte; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public int getCantidadGrupos() { return cantidadGrupos; }
    public void setCantidadGrupos(int cantidadGrupos) { this.cantidadGrupos = cantidadGrupos; }
    public int getClasificadosPorGrupo() { return clasificadosPorGrupo; }
    public void setClasificadosPorGrupo(int clasificadosPorGrupo) { this.clasificadosPorGrupo = clasificadosPorGrupo; }
}