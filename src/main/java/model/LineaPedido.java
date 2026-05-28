package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lineas_pedido")
public class LineaPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private int cantidad;
    private double subtotal;

    public LineaPedido() {}

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public void calcularSubtotal() {
        subtotal = producto.getPrecio() * cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void mostrarLinea() {
        System.out.println(producto.getNombre() +
                " x" + cantidad +
                " = $" + subtotal);
    }
}
