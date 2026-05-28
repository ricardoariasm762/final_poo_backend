package controller;

import dto.OpcionTreeDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.OpcionService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/opciones")
public class OpcionController {

    private final OpcionService opcionService;

    public OpcionController(OpcionService opcionService) {
        this.opcionService = opcionService;
    }

    @GetMapping("/menu")
    public List<OpcionTreeDTO> obtenerMenu() {
        return opcionService.listarMenuArbol();
    }
}
