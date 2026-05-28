package model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "reembolsos")
public class Reembolso {
    @Id
    private String id;

    @Column(nullable = false)
    private String idPago;

    @Column(nullable = false)
    private double monto;

    @Column(nullable = false)
    private String motivo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(nullable = false)
    private String estado; // "PENDIENTE", "PROCESADO"

    // Constructor sin argumentos requerido por JPA
    public Reembolso() {}

    public Reembolso(String id, String idPago, double monto, String motivo) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del reembolso debe ser mayor que cero.");
        }
        this.id = id;
        this.idPago = idPago;
        this.monto = monto;
        this.motivo = motivo;
        this.fecha = new Date();
        this.estado = "PENDIENTE";
    }

    public void procesarReembolso() {
        if ("PENDIENTE".equals(this.estado)) {
            this.estado = "PROCESADO";
            System.out.println("Reembolso #" + id + " para pago #" + idPago + " procesado exitosamente.");
        }
    }

    public String getId() { return id; }
    public String getIdPago() { return idPago; }
    public double getMonto() { return monto; }
    public String getMotivo() { return motivo; }
    public Date getFecha() { return fecha; }
    public String getEstado() { return estado; }

    @Override
    public String toString() {
        return "Reembolso{" +
                "id='" + id + '\'' +
                ", idPago='" + idPago + '\'' +
                ", monto=" + monto +
                ", motivo='" + motivo + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
