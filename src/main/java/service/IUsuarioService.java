package service;

import dto.AuthResponseDTO;

public interface IUsuarioService {
    AuthResponseDTO login(String usuario, String contrasena);

    String obtenerNuevoToken(String refreshToken);
}
