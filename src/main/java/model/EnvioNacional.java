package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("NACIONAL")
public class EnvioNacional extends Envio {
    public EnvioNacional() {}

    public EnvioNacional(String id, double peso, String destino) {
        super(id, peso, destino);
    }

    @Override
    public double calcularCostoEnvio() {
        return getPeso() * 15;
    }
}
