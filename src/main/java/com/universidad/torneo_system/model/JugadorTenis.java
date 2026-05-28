package com.universidad.torneo_system.model;

import com.universidad.torneo_system.model.enums.TipoDeporte;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "jugadores_tenis")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class JugadorTenis extends BaseParticipante {

    private String nacionalidad;
    private int ranking;
    private String manoHabil; // DIESTRA, ZURDA

    @Override
    public TipoDeporte getDeporte() {
        return TipoDeporte.TENIS;
    }

    @Override
    public int getMaxJugadores() {
        return 1; // deporte individual
    }

    // POLIMORFISMO: lógica exclusiva del tenis
    @Override
    public int calcularPuntos(Resultado resultado, boolean esLocal) {
        if (resultado == null) return 0;

        int setsGanados = esLocal
            ? (resultado.getSetsLocal()     != null ? resultado.getSetsLocal()     : 0)
            : (resultado.getSetsVisitante() != null ? resultado.getSetsVisitante() : 0);

        boolean gano = esLocal
            ? resultado.esVictoriaLocal()
            : resultado.esVictoriaVisitante();

        // bonus de 2 puntos por ganar el partido
        return gano ? setsGanados + 2 : setsGanados;
    }

    // Getters y Setters explícitos para evitar errores de IDE sin Lombok
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public int getRanking() { return ranking; }
    public void setRanking(int ranking) { this.ranking = ranking; }
    public String getManoHabil() { return manoHabil; }
    public void setManoHabil(String manoHabil) { this.manoHabil = manoHabil; }
}