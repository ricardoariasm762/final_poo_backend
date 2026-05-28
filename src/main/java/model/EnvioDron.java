package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DRON")
public class EnvioDron extends Envio {
    public EnvioDron() {}

    public EnvioDron(String id, double peso, String destino) {
        super(id, peso, destino);
    }

    @Override
    public double calcularCostoEnvio() {
        return getPeso() * 25;
    }
}
