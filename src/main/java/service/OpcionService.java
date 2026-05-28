package service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dto.OpcionTreeDTO;
import model.Opcion;
import repository.OpcionRepository;

@Service
public class OpcionService {

    private final OpcionRepository opcionRepository;

    public OpcionService(OpcionRepository opcionRepository) {
        this.opcionRepository = opcionRepository;
    }

    public List<OpcionTreeDTO> listarMenuArbol() {
        List<Opcion> opciones = opcionRepository.findAllByOrderByIdAsc();

        Map<Long, List<Opcion>> hijosPorPadre = opciones.stream()
                .filter(o -> o.getPadreOpcion() != null)
                .collect(Collectors.groupingBy(o -> o.getPadreOpcion().getId()));

        List<Opcion> raices = opciones.stream()
                .filter(o -> o.getPadreOpcion() == null)
                .toList();

        return raices.stream()
                .map(raiz -> mapRecursivo(raiz, hijosPorPadre))
                .toList();
    }

    private OpcionTreeDTO mapRecursivo(Opcion actual, Map<Long, List<Opcion>> hijosPorPadre) {
        OpcionTreeDTO dto = new OpcionTreeDTO(actual.getId(), actual.getNombre(), actual.getRuta());
        List<Opcion> hijos = hijosPorPadre.getOrDefault(actual.getId(), List.of());
        dto.setChildren(hijos.stream().map(h -> mapRecursivo(h, hijosPorPadre)).toList());
        return dto;
    }
}
