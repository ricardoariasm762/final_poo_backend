package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "repartidores")
public class Repartidor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRepartidor;

    @Column(nullable = false)
    private String nombreRepartidor;

    @Column(nullable = false)
    private String telefonoRepartidor;

    // Constructor sin argumentos requerido por JPA
    public Repartidor() {}

    // Constructor
    public Repartidor(String nombreRepartidor, String telefonoRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
        this.telefonoRepartidor = telefonoRepartidor;
    }

    // Getters
    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public String getTelefonoRepartidor() {
        return telefonoRepartidor;
    }

    // Setters
    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    public void setTelefonoRepartidor(String telefonoRepartidor) {
        this.telefonoRepartidor = telefonoRepartidor;
    }

    // Método toString
    @Override
    public String toString() {
        return "Repartidor: " + nombreRepartidor +
               " | Teléfono: " + telefonoRepartidor;
    }
}
