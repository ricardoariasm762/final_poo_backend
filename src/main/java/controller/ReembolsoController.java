package controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Reembolso;
import service.ReembolsoService;

@RestController
@RequestMapping("/api/reembolsos")
public class ReembolsoController {
    private final ReembolsoService service;

    public ReembolsoController(ReembolsoService service) {
        this.service = service;
    }

    @PostMapping
    public String registrarReembolso(@RequestParam String id, @RequestParam String idPago, @RequestParam double monto, @RequestParam String motivo) {
        try {
            Reembolso r = new Reembolso(id, idPago, monto, motivo);
            service.registrarReembolso(r);
            return "Reembolso #" + id + " para pago #" + idPago + " registrado exitosamente.";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Reembolso> obtenerReembolsos() {
        return service.listarReembolsos();
    }
}
