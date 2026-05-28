package com.universidad.torneo_system.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resultado {

    private Integer puntosLocal;
    private Integer puntosVisitante;

    // para tenis: sets ganados por cada jugador
    private Integer setsLocal;
    private Integer setsVisitante;

    public boolean esVictoriaLocal() {
        if (setsLocal != null && setsVisitante != null) {
            return setsLocal > setsVisitante;
        }
        return puntosLocal != null && puntosVisitante != null
               && puntosLocal > puntosVisitante;
    }

    public boolean esVictoriaVisitante() {
        if (setsLocal != null && setsVisitante != null) {
            return setsVisitante > setsLocal;
        }
        return puntosLocal != null && puntosVisitante != null
               && puntosVisitante > puntosLocal;
    }

    public boolean esEmpate() {
        if (setsLocal != null && setsVisitante != null) {
            return setsLocal.equals(setsVisitante);
        }
        return puntosLocal != null && puntosVisitante != null
               && puntosLocal.equals(puntosVisitante);
    }

    // Getters y Setters explícitos para evitar errores de IDE sin Lombok
    public Integer getPuntosLocal() { return puntosLocal; }
    public void setPuntosLocal(Integer puntosLocal) { this.puntosLocal = puntosLocal; }
    public Integer getPuntosVisitante() { return puntosVisitante; }
    public void setPuntosVisitante(Integer puntosVisitante) { this.puntosVisitante = puntosVisitante; }
    public Integer getSetsLocal() { return setsLocal; }
    public void setSetsLocal(Integer setsLocal) { this.setsLocal = setsLocal; }
    public Integer getSetsVisitante() { return setsVisitante; }
    public void setSetsVisitante(Integer setsVisitante) { this.setsVisitante = setsVisitante; }
}