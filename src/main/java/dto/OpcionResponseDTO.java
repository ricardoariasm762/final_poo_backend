package dto;

public class OpcionResponseDTO {

    private Long id;
    private String nombre;
    private Long padreOpcionId;
    private String ruta;

    public OpcionResponseDTO() {
    }

    public OpcionResponseDTO(Long id, String nombre, Long padreOpcionId, String ruta) {
        this.id = id;
        this.nombre = nombre;
        this.padreOpcionId = padreOpcionId;
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

    public Long getPadreOpcionId() {
        return padreOpcionId;
    }

    public void setPadreOpcionId(Long padreOpcionId) {
        this.padreOpcionId = padreOpcionId;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
