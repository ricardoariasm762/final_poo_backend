package controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Pedido;
import model.Repartidor;
import service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> registrarPedido(@RequestBody java.util.Map<String, Object> payload) {
        try {
            
            String nombreCliente = (String) payload.get("nombreCliente");
            
            
            List<Integer> productoIds = (List<Integer>) payload.get("productoIds");
            
            
            Pedido pedido = new Pedido();
            if (payload.get("impuesto") != null) pedido.setImpuesto(Double.parseDouble(payload.get("impuesto").toString()));
            
            service.registrarPedido(pedido, nombreCliente, productoIds);
            return ResponseEntity.ok("Pedido registrado correctamente para: " + nombreCliente + ". Total con IVA: $" + String.format("%.2f", pedido.getTotal()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar pedido: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return service.listarPedidos();
    }

    @PostMapping("/{id}/envio")
    public ResponseEntity<String> realizarEnvio(@PathVariable int id, @RequestParam String nombreRepartidor) {
        try {
            Pedido p = service.buscarPorId(id).orElseThrow(() -> new Exception("Pedido no encontrado"));
            service.confirmarEnvio(p, new Repartidor(nombreRepartidor, "000"));
            return ResponseEntity.ok("Envío realizado para pedido #" + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en envío: " + e.getMessage());
        }
    }
}
