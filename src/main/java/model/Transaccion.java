package model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    private String id;

    @Column(nullable = false)
    private String idPago;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(nullable = false)
    private String tipo; // "DEBITO", "CREDITO"

    @Column(nullable = false)
    private String estado; // "EXITOSA", "FALLIDA"

    // Constructor sin argumentos requerido por JPA
    public Transaccion() {}

    public Transaccion(String id, String idPago, String tipo) {
        this.id = id;
        this.idPago = idPago;
        this.tipo = tipo;
        this.fecha = new Date();
        this.estado = "EXITOSA"; // Por defecto
    }

    public String getId() { return id; }
    public String getIdPago() { return idPago; }
    public Date getFecha() { return fecha; }
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id='" + id + '\'' +
                ", idPago='" + idPago + '\'' +
                ", tipo='" + tipo + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
