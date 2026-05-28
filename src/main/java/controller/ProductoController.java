package controller;

import model.Producto;
import service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto producto) {
        try {
            Producto guardado = service.guardar(producto);
            return ResponseEntity.ok(guardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Producto> obtenerProductos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable int id) {
        Producto producto = service.buscarPorId(id);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable int id, @RequestBody Producto datos) {
        try {
            Producto actualizado = service.actualizar(id, datos);
            if (actualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
            }
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar producto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id) {
        boolean eliminado = service.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
        return ResponseEntity.ok("Producto eliminado correctamente.");
    }
}
