package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProducto;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    // Constructor sin argumentos requerido por JPA
    public Producto() {}

    // Constructor con argumentos
    public Producto(int idProducto, String nombre, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    // Setters (Requeridos por Jackson para mapeo JSON)
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        } else {
            System.out.println("El precio no puede ser negativo.");
        }
    }

    // Método toString para imprimir el producto
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
