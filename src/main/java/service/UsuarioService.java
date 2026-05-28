package service;

import dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import util.JwtUtil;

@Service
public class UsuarioService implements IUsuarioService {

    private final JwtUtil jwtUtil;

    @Value("${auth.usuario:admin}")
    private String usuarioAdmin;

    @Value("${auth.contrasena:admin}")
    private String contrasenaAdmin;

    @Value("${auth.rol:ADMIN}")
    private String rolAdmin;

    public UsuarioService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponseDTO login(String usuario, String contrasena) {
        if (usuario == null || contrasena == null) {
            return null;
        }

        if (!usuarioAdmin.equals(usuario) || !contrasenaAdmin.equals(contrasena)) {
            return null;
        }

        String token = jwtUtil.generarToken(usuario, rolAdmin);
        String refreshToken = jwtUtil.generarRefreshToken(usuario);
        return new AuthResponseDTO(token, refreshToken);
    }

    @Override
    public String obtenerNuevoToken(String refreshToken) {
        if (refreshToken == null || !jwtUtil.esRefreshTokenValido(refreshToken)) {
            return null;
        }

        String usuario = jwtUtil.obtenerUsuarioDelToken(refreshToken);
        if (usuario == null || !usuarioAdmin.equals(usuario)) {
            return null;
        }

        return jwtUtil.generarToken(usuario, rolAdmin);
    }
}
