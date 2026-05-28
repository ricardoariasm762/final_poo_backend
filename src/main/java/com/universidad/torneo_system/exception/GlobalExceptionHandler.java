package com.universidad.torneo_system.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 — recurso no encontrado
    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(RecursoNoEncontradoException ex) {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Recurso no encontrado");
        res.setMensaje(ex.getMessage());
        res.setTimestamp(LocalDateTime.now());
        return res;
    }

    // 400 — estado inválido del torneo o encuentro
    @ExceptionHandler(EstadoInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleEstadoInvalido(EstadoInvalidoException ex) {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Estado inválido");
        res.setMensaje(ex.getMessage());
        res.setTimestamp(LocalDateTime.now());
        return res;
    }

    // 400 — errores de validación @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidacion(MethodArgumentNotValidException ex) {
        List<String> detalles = ex.getBindingResult()
            .getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .toList();

        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Error de validación");
        res.setMensaje("Revisa los campos enviados");
        res.setDetalles(detalles);
        res.setTimestamp(LocalDateTime.now());
        return res;
    }

    // 401 — credenciales incorrectas
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleBadCredentials(BadCredentialsException ex) {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Credenciales inválidas");
        res.setMensaje("Username o contraseña incorrectos");
        res.setTimestamp(LocalDateTime.now());
        return res;
    }

    // 401 — token expirado
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleTokenExpirado(ExpiredJwtException ex) {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Token expirado");
        res.setMensaje("El token JWT ha expirado, inicia sesión nuevamente");
        res.setTimestamp(LocalDateTime.now());
        return res;
    }

    // 401 — token malformado
    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleTokenMalformado(MalformedJwtException ex) {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Token inválido");
        res.setMensaje("El token JWT no tiene el formato correcto");
        res.setTimestamp(LocalDateTime.now());
        return res;
    }

    // 403 — sin permisos
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccesoDenegado(AccessDeniedException ex) {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(403);
        res.setError("Acceso denegado");
        res.setMensaje("No tienes permisos para realizar esta acción");
        res.setTimestamp(LocalDateTime.now());
        return res;
    }

    // 500 — error genérico
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneric(Exception ex) {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(500);
        res.setError("Error interno");
        res.setMensaje("Ocurrió un error inesperado: " + ex.getMessage());
        res.setTimestamp(LocalDateTime.now());
        return res;
    }
}