package controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Cliente;
import service.ClienteService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> registrarCliente(@RequestBody Cliente cliente) {
        try {
            service.registrarCliente(cliente);
            return ResponseEntity.ok("Cliente " + cliente.getNombre() + " registrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar cliente: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return service.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable String id) {
        try {
            Cliente cliente = service.buscarCliente(UUID.fromString(id));
            if (cliente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
            }
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID de cliente invalido");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable String id, @RequestBody Cliente datos) {
        try {
            Cliente actualizado = service.actualizarCliente(UUID.fromString(id), datos);
            if (actualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
            }
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar cliente: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable String id) {
        try {
            boolean eliminado = service.eliminarCliente(UUID.fromString(id));
            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
            }
            return ResponseEntity.ok("Cliente eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID de cliente invalido");
        }
    }
}
