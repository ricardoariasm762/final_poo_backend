package service;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Pedido;
import model.Repartidor;
import repository.ClienteRepository;
import repository.PedidoRepository;
import repository.ProductoRepository;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository repository, ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    public void registrarPedido(Pedido pedido, String nombreCliente, List<Integer> productoIds) throws Exception {
        
        model.Cliente cliente = clienteRepository.findByNombre(nombreCliente)
                .orElseThrow(() -> new Exception("Cliente no encontrado con el nombre: " + nombreCliente));
        
        pedido.setCliente(cliente);

        
        if (productoIds != null && !productoIds.isEmpty()) {
            List<model.Producto> productos = productoRepository.findAllById(productoIds);
            pedido.setProductos(productos);
        }

        
        pedido.recalcularTotal();

        
        if (pedido.getFecha() == null) {
            pedido.setFecha(new java.util.Date());
        }
        if (pedido.getEstado() == null) {
            pedido.setEstado(model.EstadoPedido.CREADO);
        }
        
        repository.save(pedido);
        System.out.println("Service: Pedido guardado exitosamente para el cliente: " + nombreCliente + ". Total: $" + pedido.getTotal());
    }

    public List<Pedido> listarPedidos() {
        return repository.findAll();
    }

    public java.util.Optional<Pedido> buscarPorId(int id) {
        return repository.findById(id);
    }

    public void confirmarEnvio(Pedido pedido, Repartidor repartidor) {
        pedido.confirmar();
        pedido.pagar();
        pedido.enviar(repartidor);
        pedido.entregar();
        
        repository.save(pedido);
    }
}
