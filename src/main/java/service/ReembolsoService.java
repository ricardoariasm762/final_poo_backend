package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Reembolso;

import org.springframework.stereotype.Service;
import repository.ReembolsoRepository;

@Service
public class ReembolsoService {
    private final ReembolsoRepository repository;

    public ReembolsoService(ReembolsoRepository repository) {
        this.repository = repository;
    }

    public void registrarReembolso(Reembolso r) {
        r.procesarReembolso();
        repository.save(r);
        System.out.println("Service: Reembolso guardado con Spring Data JPA.");
    }

    public List<Reembolso> listarReembolsos() {
        return repository.findAll();
    }

    public Optional<Reembolso> buscarPorId(String id) {
        return repository.findById(id);
    }
}
