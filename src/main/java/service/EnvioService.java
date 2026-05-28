package service;

import java.util.ArrayList;
import java.util.List;
import model.Envio;

import org.springframework.stereotype.Service;
import repository.EnvioRepository;

@Service
public class EnvioService {
    private final EnvioRepository repository;

    public EnvioService(EnvioRepository repository) {
        this.repository = repository;
    }

    public void registrarEnvio(Envio envio) {
        repository.save(envio);
        System.out.println("Service: Envío guardado con Spring Data JPA.");
    }

    public List<Envio> listarEnvios() {
        return repository.findAll();
    }
}
