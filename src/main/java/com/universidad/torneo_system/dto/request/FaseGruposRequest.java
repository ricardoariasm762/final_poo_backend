package com.universidad.torneo_system.dto.request;

import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaseGruposRequest {

    // ejemplo: { "A": [1,2,3], "B": [4,5,6] }
    @NotEmpty(message = "Debe enviar al menos un grupo con participantes")
    private Map<String, List<Long>> grupos;

    // Getters y Setters explícitos
    public Map<String, List<Long>> getGrupos() { return grupos; }
    public void setGrupos(Map<String, List<Long>> grupos) { this.grupos = grupos; }
}