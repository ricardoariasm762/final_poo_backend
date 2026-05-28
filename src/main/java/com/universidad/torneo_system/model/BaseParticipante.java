package com.universidad.torneo_system.model;

import com.universidad.torneo_system.model.enums.TipoDeporte;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseParticipante implements Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String entrenador;

    // cada subclase devuelve su propio enum
    @Override
    public boolean esAptoParaFase() {
        return nombre != null && !nombre.isBlank();
    }

    @Override
    public abstract TipoDeporte getDeporte();

    @Override
    public abstract int getMaxJugadores();

    @Override
    public abstract int calcularPuntos(Resultado resultado, boolean esLocal);

    // Getters y Setters explícitos para evitar errores de IDE sin Lombok
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getEntrenador() { return entrenador; }
    public void setEntrenador(String entrenador) { this.entrenador = entrenador; }
}