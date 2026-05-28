package com.universidad.torneo_system.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoFutbolRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotBlank(message = "El entrenador es obligatorio")
    private String entrenador;

    private String estadio;
    private String colorCamiseta;

    @Min(value = 11, message = "Mínimo 11 jugadores")
    private int numeroPlantel;

    // Getters y Setters explícitos
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getEntrenador() { return entrenador; }
    public void setEntrenador(String entrenador) { this.entrenador = entrenador; }
    public String getEstadio() { return estadio; }
    public void setEstadio(String estadio) { this.estadio = estadio; }
    public String getColorCamiseta() { return colorCamiseta; }
    public void setColorCamiseta(String colorCamiseta) { this.colorCamiseta = colorCamiseta; }
    public int getNumeroPlantel() { return numeroPlantel; }
    public void setNumeroPlantel(int numeroPlantel) { this.numeroPlantel = numeroPlantel; }
}