package com.universidad.torneo_system.model;

import com.universidad.torneo_system.model.enums.TipoDeporte;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "equipos_futbol")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EquipoFutbol extends BaseParticipante {

    private String estadio;
    private String colorCamiseta;

    @Column(nullable = false)
    private int numeroPlantel; // jugadores inscritos

    @Override
    public TipoDeporte getDeporte() {
        return TipoDeporte.FUTBOL;
    }

    @Override
    public int getMaxJugadores() {
        return 11;
    }

    // POLIMORFISMO: lógica exclusiva del fútbol
    @Override
    public int calcularPuntos(Resultado resultado, boolean esLocal) {
        if (resultado == null) return 0;

        if (resultado.esEmpate())                         return 1;
        if (esLocal && resultado.esVictoriaLocal())       return 3;
        if (!esLocal && resultado.esVictoriaVisitante())  return 3;
        return 0;
    }

    // Getters y Setters explícitos para evitar errores de IDE sin Lombok
    public String getEstadio() { return estadio; }
    public void setEstadio(String estadio) { this.estadio = estadio; }
    public String getColorCamiseta() { return colorCamiseta; }
    public void setColorCamiseta(String colorCamiseta) { this.colorCamiseta = colorCamiseta; }
    public int getNumeroPlantel() { return numeroPlantel; }
    public void setNumeroPlantel(int numeroPlantel) { this.numeroPlantel = numeroPlantel; }
}