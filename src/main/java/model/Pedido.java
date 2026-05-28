package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPedido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    private double total;
    private double impuesto;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "pedidos_productos",
        joinColumns = @JoinColumn(name = "id_pedido"),
        inverseJoinColumns = @JoinColumn(name = "id_producto")
    )
    private List<Producto> productos;

    @ManyToOne
    @JoinColumn(name = "id_repartidor")
    private Repartidor repartidor;

    public Pedido() {}

    public Pedido(int idPedido, Cliente cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.fecha = new Date();
        this.estado = EstadoPedido.CREADO;
        this.productos = new ArrayList<>();
        this.total = 0.0;
        this.impuesto = 0.0;
    }


    public void agregarProducto(Producto producto) {
        productos.add(producto);
        recalcularTotal();
    }

    public void recalcularTotal() {
        double subtotal = 0;
        if (productos != null) {
            for (Producto p : productos) {
                subtotal += p.getPrecio();
            }
        }

        
        this.total = subtotal * (1 + impuesto);
    }

    public void aplicarImpuesto(double impuesto) {
        this.impuesto = impuesto;
        recalcularTotal();
    }



    public void confirmar() {
        if (estado == EstadoPedido.CREADO) {
            estado = EstadoPedido.CONFIRMADO;
            System.out.println("Pedido confirmado.");
        } else {
            System.out.println("No se puede confirmar. Estado actual: " + estado);
        }
    }

    public void pagar() {
        if (estado == EstadoPedido.CONFIRMADO) {
            estado = EstadoPedido.PAGADO;
            System.out.println("Pedido pagado.");
        } else {
            System.out.println("No se puede pagar. Estado actual: " + estado);
        }
    }

    public void enviar(Repartidor repartidor) {
        if (estado == EstadoPedido.PAGADO) {
            this.repartidor = repartidor;
            estado = EstadoPedido.ENVIADO;
            System.out.println("Pedido enviado.");
        } else {
            System.out.println("No se puede enviar. Estado actual: " + estado);
        }
    }

    public void entregar() {
        if (estado == EstadoPedido.ENVIADO) {
            estado = EstadoPedido.ENTREGADO;
            System.out.println("Pedido entregado.");
        } else {
            System.out.println("No se puede entregar. Estado actual: " + estado);
        }
    }

    public void cancelar() {
        if (estado != EstadoPedido.ENTREGADO && estado != EstadoPedido.CANCELADO) {
            estado = EstadoPedido.CANCELADO;
            System.out.println("Pedido cancelado.");
        } else {
            System.out.println("No se puede cancelar. Estado actual: " + estado);
        }
    }


    public int getIdPedido() {
        return idPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }
    public void setTotal(double total) { this.total = total; }
    public void setImpuesto(double impuesto) { 
        this.impuesto = impuesto; 
        recalcularTotal();
    }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public void setProductos(List<Producto> productos) { 
        this.productos = productos; 
        recalcularTotal();
    }
    public void setRepartidor(Repartidor repartidor) { this.repartidor = repartidor; }


    @Override
    public String toString() {

        String infoProductos = "";
        for (Producto p : productos) {
            infoProductos += "\n   - " + p.getNombre() + " | $" + p.getPrecio();
        }

        return "\n========= PEDIDO =========" +
                "\nID: " + idPedido +
                "\nFecha: " + fecha +
                "\nCliente: " + cliente.getNombre() +
                "\nEstado: " + estado +
                "\nRepartidor: " + (repartidor != null ? repartidor.getNombreRepartidor() : "No asignado") +
                "\nProductos: " + (productos.isEmpty() ? "Sin productos" : infoProductos) +
                "\nImpuesto: " + (impuesto * 100) + "%" +
                "\nTOTAL: $" + total +
                "\n==========================\n";
    }
}
