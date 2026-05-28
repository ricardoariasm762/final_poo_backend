package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("REGULAR")
public class EnvioRegular extends Envio {
    public EnvioRegular() {}

    public EnvioRegular(String id, double peso, String destino) {
        super(id, peso, destino);
    }

    @Override
    public double calcularCostoEnvio() {
        return getPeso() * 10;
    }
}
