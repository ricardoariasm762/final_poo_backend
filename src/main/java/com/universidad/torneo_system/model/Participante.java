package com.universidad.torneo_system.model;

import com.universidad.torneo_system.model.enums.TipoDeporte;

public interface Participante {

    Long getId();
    String getNombre();
    TipoDeporte getDeporte();
    int getMaxJugadores();

    // MÉTODO CLAVE DEL POLIMORFISMO
    // cada clase concreta lo implementa con sus propias reglas
    int calcularPuntos(Resultado resultado, boolean esLocal);

    // para validar si puede pasar a fase de eliminación
    boolean esAptoParaFase();
}