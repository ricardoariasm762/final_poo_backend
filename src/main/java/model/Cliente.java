package model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue
    private UUID idCliente;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=true)
    private String telefono;

    @Column(nullable=true)
    private String direccion;

    @Column(nullable = false)
    private boolean activo;

    @Column(length = 10, nullable = false)
    private String numIdentificacion;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private TipoIdentificacion tipoIdentificacion;

    public Cliente() {}

    public Cliente(String nombre, String email, String telefono, String direccion, String numIdentificacion, Genero genero, TipoIdentificacion tipoIdentificacion) {
        validarIdentificacion(numIdentificacion);
        this.nombre = nombre;
        this.numIdentificacion = numIdentificacion;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.activo = true;
        this.genero = genero;
        this.tipoIdentificacion = tipoIdentificacion;
    }

    private void validarIdentificacion(String numId) {
        if (numId != null && numId.length() > 10) {
            throw new IllegalArgumentException("Error: El número de identificación no puede tener más de 10 caracteres.");
        }
    }

    public void actualizarDatos(String nombre, String email, String telefono, String direccion, String numIdentificacion, Genero genero, TipoIdentificacion tipoIdentificacion) {
        if (!activo) {
            System.out.println("Cliente inactivo. No se pueden actualizar datos.");
            return;
        }
        validarIdentificacion(numIdentificacion);
        this.nombre = nombre;
        this.numIdentificacion = numIdentificacion;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.genero = genero;
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public void desactivar() {
        this.activo = false;
    }

    // Getters
    public UUID getIdCliente() { return idCliente; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public boolean isActivo() { return activo; }
    public Genero getGenero() { return genero; }
    public String getNumIdentificacion() { return numIdentificacion; }
    public TipoIdentificacion getTipoIdentificacion() { return tipoIdentificacion; }

    // Setters (Requeridos por Jackson para mapeo JSON)
    public void setIdCliente(UUID idCliente) { this.idCliente = idCliente; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public void setNumIdentificacion(String numIdentificacion) { 
        validarIdentificacion(numIdentificacion);
        this.numIdentificacion = numIdentificacion; 
    }
    public void setGenero(Genero genero) { this.genero = genero; }
    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) { this.tipoIdentificacion = tipoIdentificacion; }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " | Email: " + email;
    }
}
