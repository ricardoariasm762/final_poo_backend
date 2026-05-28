package controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Genero;
import model.Pago;
import model.Pedido;
import model.TipoIdentificacion;
import service.PagoService;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService service;

    public PagoController(PagoService service) {
        this.service = service;
    }

    @PostMapping
    public String registrarPago(@RequestParam String id, @RequestParam double monto) {
        try {
            Pedido pedidoDummy = new Pedido(101, new model.Cliente("Cliente Spring", "spring@mail.com", "N/A", "N/A", "000", Genero.OTRO, TipoIdentificacion.CC));
            Pago pago = new Pago(id, monto, pedidoDummy);
            service.registrarPago(pago);
            return "Pago registrado satisfactoriamente para ID: " + id;
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/realizar")
    public String realizarPago(@RequestParam String id) {
        service.realizarPago(id);
        return "Realización de pago procesada para ID: " + id;
    }

    @GetMapping
    public List<Pago> obtenerPagos() {
        return service.listarPagos();
    }
}
