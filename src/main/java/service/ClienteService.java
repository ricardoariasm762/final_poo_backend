package service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import model.Cliente;
import repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void registrarCliente(Cliente cliente) {
        // Aseguramos que el estado activo sea true por defecto
        if (!cliente.isActivo()) {
            cliente.setActivo(true);
        }
        Cliente guardado = repository.save(cliente);
        System.out.println("Service: Cliente guardado exitosamente con ID: " + guardado.getIdCliente());
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Cliente buscarCliente(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Cliente actualizarCliente(UUID id, Cliente datos) {
        Cliente existente = repository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }

        existente.actualizarDatos(
                datos.getNombre(),
                datos.getEmail(),
                datos.getTelefono(),
                datos.getDireccion(),
                datos.getNumIdentificacion(),
                datos.getGenero(),
                datos.getTipoIdentificacion()
        );

        return repository.save(existente);
    }

    public boolean eliminarCliente(UUID id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
