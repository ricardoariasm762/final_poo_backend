package dto;

import java.util.ArrayList;
import java.util.List;

public class OpcionTreeDTO {

    private Long id;
    private String nombre;
    private String ruta;
    private List<OpcionTreeDTO> children = new ArrayList<>();

    public OpcionTreeDTO() {
    }

    public OpcionTreeDTO(Long id, String nombre, String ruta) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public List<OpcionTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<OpcionTreeDTO> children) {
        this.children = children;
    }
}
