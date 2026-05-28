package controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Envio;
import model.EnvioDron;
import model.EnvioInternacional;
import model.EnvioNacional;
import service.EnvioService;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {
    private final EnvioService service;

    public EnvioController(EnvioService service) {
        this.service = service;
    }

    @PostMapping("/dron")
    public String registrarEnvioDron(@RequestParam String id, @RequestParam double peso, @RequestParam String destino) {
        service.registrarEnvio(new EnvioDron(id, peso, destino));
        return "Envío por Dron registrado: " + id;
    }

    @PostMapping("/nacional")
    public String registrarEnvioNacional(@RequestParam String id, @RequestParam double peso, @RequestParam String destino) {
        service.registrarEnvio(new EnvioNacional(id, peso, destino));
        return "Envío Nacional registrado: " + id;
    }

    @PostMapping("/internacional")
    public String registrarEnvioInternacional(@RequestParam String id, @RequestParam double peso, @RequestParam String destino) {
        service.registrarEnvio(new EnvioInternacional(id, peso, destino));
        return "Envío Internacional registrado: " + id;
    }

    @GetMapping
    public List<Envio> obtenerEnvios() {
        return service.listarEnvios();
    }
}
