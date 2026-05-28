package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INTERNACIONAL")
public class EnvioInternacional extends Envio {
    public EnvioInternacional() {}

    public EnvioInternacional(String id, double peso, String destino) {
        super(id, peso, destino);
    }

    @Override
    public double calcularCostoEnvio() {
        return getPeso() * 20;
    }
}
