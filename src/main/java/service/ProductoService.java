package service;

import model.Producto;
import repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    public List<Producto> buscarPorIds(List<Integer> ids) {
        return repository.findAllById(ids);
    }

    public Producto buscarPorId(int id) {
        return repository.findById(id).orElse(null);
    }

    public Producto actualizar(int id, Producto datos) {
        Producto existente = repository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }

        existente.setNombre(datos.getNombre());
        existente.setPrecio(datos.getPrecio());
        return repository.save(existente);
    }

    public boolean eliminar(int id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
