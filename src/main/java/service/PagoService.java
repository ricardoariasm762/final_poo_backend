package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Pago;

import org.springframework.stereotype.Service;
import repository.PagoRepository;

@Service
public class PagoService {

    private final PagoRepository repository;

    public PagoService(PagoRepository repository) {
        this.repository = repository;
    }

    public void registrarPago(Pago pago) {
        repository.save(pago);
        System.out.println("Service: Pago guardado con Spring Data JPA.");
    }

    public void realizarPago(String idPago) {
        buscarPagoPorId(idPago).ifPresentOrElse(
            p -> {
                p.realizarPago();
                repository.save(p);
                System.out.println("Service: Pago realizado y actualizado en DB.");
            },
            () -> System.err.println("Service Error: Pago no encontrado con ID: " + idPago)
        );
    }

    public List<Pago> listarPagos() {
        return repository.findAll();
    }

    public Optional<Pago> buscarPagoPorId(String id) {
        return repository.findById(id);
    }
}
