package config;

import model.Opcion;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repository.OpcionRepository;

@Component
public class OpcionDataInitializer implements CommandLineRunner {

    private final OpcionRepository opcionRepository;

    public OpcionDataInitializer(OpcionRepository opcionRepository) {
        this.opcionRepository = opcionRepository;
    }

    @Override
    public void run(String... args) {
        if (opcionRepository.count() > 0) {
            return;
        }

        Opcion raiz = opcionRepository.save(new Opcion("Mi aplicacion de menus", null, null));

        Opcion clientes = opcionRepository.save(new Opcion("Clientes", raiz, null));
        opcionRepository.save(new Opcion("Listar clientes", clientes, "/clientes"));
        opcionRepository.save(new Opcion("Crear cliente", clientes, "/clientes/nuevo"));
        opcionRepository.save(new Opcion("Editar cliente", clientes, "/clientes/editar"));
        opcionRepository.save(new Opcion("Eliminar cliente", clientes, "/clientes/eliminar"));

        Opcion productos = opcionRepository.save(new Opcion("Productos", raiz, null));
        opcionRepository.save(new Opcion("Listar productos", productos, "/productos"));
        opcionRepository.save(new Opcion("Crear producto", productos, "/productos/nuevo"));
        opcionRepository.save(new Opcion("Categorias", productos, "/productos/categorias"));
    }
}
