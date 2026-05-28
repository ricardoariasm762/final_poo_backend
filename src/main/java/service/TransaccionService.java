package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Transaccion;

import org.springframework.stereotype.Service;
import repository.TransaccionRepository;

@Service
public class TransaccionService {
    private final TransaccionRepository repository;

    public TransaccionService(TransaccionRepository repository) {
        this.repository = repository;
    }

    public void registrarTransaccion(Transaccion t) {
        repository.save(t);
        System.out.println("Service: Transacción guardada con Spring Data JPA.");
    }

    public List<Transaccion> listarTransacciones() {
        return repository.findAll();
    }

    public Optional<Transaccion> buscarPorId(String id) {
        return repository.findById(id);
    }
}
