package model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "envios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_envio", discriminatorType = DiscriminatorType.STRING)
public abstract class Envio {
    @Id
    private String id;
    private double peso;
    private String destino;

    public Envio() {}

    public Envio(String id, double peso, String destino) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero.");
        }
        this.id = id;
        this.peso = peso;
        this.destino = destino;
    }

    public abstract double calcularCostoEnvio();

    public String getId() { return id; }
    public double getPeso() { return peso; }
    public String getDestino() { return destino; }

    @Override
    public String toString() {
        return String.format("Envio[ID=%s, Peso=%.2f, Destino=%s, Costo=$%.2f]", 
                             id, peso, destino, calcularCostoEnvio());
    }
}
