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
@Table(name = "equipos_baloncesto")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EquipoBaloncesto extends BaseParticipante {

    private String cancha;
    private String conferencia;

    @Column(nullable = false)
    private int numeroPlantel;

    @Override
    public TipoDeporte getDeporte() {
        return TipoDeporte.BALONCESTO;
    }

    @Override
    public int getMaxJugadores() {
        return 5;
    }

    // POLIMORFISMO: lógica exclusiva del baloncesto
    @Override
    public int calcularPuntos(Resultado resultado, boolean esLocal) {
        if (resultado == null) return 0;

        // en baloncesto no existe empate
        if (esLocal && resultado.esVictoriaLocal())       return 2;
        if (!esLocal && resultado.esVictoriaVisitante())  return 2;
        return 1; // derrota igual suma 1 punto
    }

    // Getters y Setters explícitos para evitar errores de IDE sin Lombok
    public String getCancha() { return cancha; }
    public void setCancha(String cancha) { this.cancha = cancha; }
    public String getConferencia() { return conferencia; }
    public void setConferencia(String conferencia) { this.conferencia = conferencia; }
    public int getNumeroPlantel() { return numeroPlantel; }
    public void setNumeroPlantel(int numeroPlantel) { this.numeroPlantel = numeroPlantel; }
}