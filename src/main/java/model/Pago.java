package model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "pagos")
public class Pago {
    @Id
    private String id;
    
    @Column(nullable = false)
    private double monto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(nullable = false)
    private String estado; // "PENDIENTE", "PROCESADO"

    @OneToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    // Constructor sin argumentos requerido por JPA
    public Pago() {}

    public Pago(String id, double monto, Pedido pedido) {
        if (!validarMonto(monto)) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }
        this.id = id;
        this.monto = monto;
        this.pedido = pedido;
        this.fecha = new Date();
        this.estado = "PENDIENTE";
    }

    private boolean validarMonto(double monto) {
        return monto > 0;
    }

    public void realizarPago() {
        if ("PENDIENTE".equals(this.estado)) {
            System.out.println("Iniciando realización de pago...");
            this.procesarPago();
        } else {
            System.out.println("No se puede realizar un pago en estado: " + estado);
        }
    }

    public void procesarPago() {
        if ("PENDIENTE".equals(this.estado)) {
            this.estado = "PROCESADO";
            System.out.println("Pago #" + id + " procesado exitosamente.");
        } else {
            System.out.println("El pago #" + id + " ya se encuentra en estado: " + estado);
        }
    }

    // Getters públicos (los setters están restringidos según requerimiento)
    public String getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id='" + id + '\'' +
                ", monto=" + monto +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", pedidoId=" + (pedido != null ? pedido.getIdPedido() : "N/A") +
                '}';
    }
}
