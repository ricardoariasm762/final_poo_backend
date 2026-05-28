package controller;

import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.RefreshTokenRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.IUsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IUsuarioService usuarioService;

    public AuthController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {
        AuthResponseDTO response = usuarioService.login(request.getUsuario(), request.getContrasena());
        if (response == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequestDTO request) {
        String nuevoToken = usuarioService.obtenerNuevoToken(request.getRefreshToken());
        if (nuevoToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Refresh token invalido o expirado");
        }
        return ResponseEntity.ok(new AuthResponseDTO(nuevoToken, request.getRefreshToken()));
    }
}
