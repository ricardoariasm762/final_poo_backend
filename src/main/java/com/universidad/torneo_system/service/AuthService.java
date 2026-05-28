package com.universidad.torneo_system.service;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.universidad.torneo_system.config.JwtService;
import com.universidad.torneo_system.config.UserDetailsServiceImpl;
import com.universidad.torneo_system.dto.request.LoginRequest;
import com.universidad.torneo_system.dto.request.RegisterRequest;
import com.universidad.torneo_system.dto.response.AuthResponse;
import com.universidad.torneo_system.exception.EstadoInvalidoException;
import com.universidad.torneo_system.model.Usuario;
import com.universidad.torneo_system.model.enums.Rol;
import com.universidad.torneo_system.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthService(UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder,
                       JwtService jwtService, AuthenticationManager authManager,
                       UserDetailsServiceImpl userDetailsService) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepo.existsByUsername(request.getUsername())) {
            throw new EstadoInvalidoException("El username ya está en uso");
        }
        if (usuarioRepo.existsByEmail(request.getEmail())) {
            throw new EstadoInvalidoException("El email ya está registrado");
        }

        Set<Rol> roles = (request.getRoles() == null || request.getRoles().isEmpty())
            ? Set.of(Rol.ROLE_ESPECTADOR)
            : request.getRoles();

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEmail(request.getEmail());
        usuario.setRoles(roles);

        usuarioRepo.save(usuario);

        UserDetails userDetails =
            userDetailsService.loadUserByUsername(usuario.getUsername());
        String token = jwtService.generarToken(userDetails);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUsername(usuario.getUsername());
        response.setMensaje("Usuario registrado correctamente");
        return response;
    }

    public AuthResponse login(LoginRequest request) {
        // lanza excepción si las credenciales son incorrectas
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        UserDetails userDetails =
            userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generarToken(userDetails);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUsername(request.getUsername());
        response.setMensaje("Login exitoso");
        return response;
    }
}