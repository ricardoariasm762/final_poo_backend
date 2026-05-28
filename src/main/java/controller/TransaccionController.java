package controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Transaccion;
import service.TransaccionService;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {
    private final TransaccionService service;

    public TransaccionController(TransaccionService service) {
        this.service = service;
    }

    @PostMapping
    public String registrarTransaccion(@RequestParam String id, @RequestParam String idPago, @RequestParam String tipo) {
        Transaccion t = new Transaccion(id, idPago, tipo);
        service.registrarTransaccion(t);
        return "Transacción #" + id + " para pago #" + idPago + " registrada exitosamente.";
    }

    @GetMapping
    public List<Transaccion> obtenerTransacciones() {
        return service.listarTransacciones();
    }
}
