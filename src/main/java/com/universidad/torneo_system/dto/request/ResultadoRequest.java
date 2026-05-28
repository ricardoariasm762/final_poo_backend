package com.universidad.torneo_system.dto.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoRequest {

    @Min(value = 0, message = "Los puntos no pueden ser negativos")
    private Integer puntosLocal;

    @Min(value = 0, message = "Los puntos no pueden ser negativos")
    private Integer puntosVisitante;

    // solo para tenis
    private Integer setsLocal;
    private Integer setsVisitante;

    // Getters y Setters explícitos
    public Integer getPuntosLocal() { return puntosLocal; }
    public void setPuntosLocal(Integer puntosLocal) { this.puntosLocal = puntosLocal; }
    public Integer getPuntosVisitante() { return puntosVisitante; }
    public void setPuntosVisitante(Integer puntosVisitante) { this.puntosVisitante = puntosVisitante; }
    public Integer getSetsLocal() { return setsLocal; }
    public void setSetsLocal(Integer setsLocal) { this.setsLocal = setsLocal; }
    public Integer getSetsVisitante() { return setsVisitante; }
    public void setSetsVisitante(Integer setsVisitante) { this.setsVisitante = setsVisitante; }
}