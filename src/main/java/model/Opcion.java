package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "opciones")
public class Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "padre_opcion_id")
    private Opcion padreOpcion;

    @Column(length = 255)
    private String ruta;

    public Opcion() {
    }

    public Opcion(String nombre, Opcion padreOpcion, String ruta) {
        this.nombre = nombre;
        this.padreOpcion = padreOpcion;
        this.ruta = ruta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Opcion getPadreOpcion() {
        return padreOpcion;
    }

    public void setPadreOpcion(Opcion padreOpcion) {
        this.padreOpcion = padreOpcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
