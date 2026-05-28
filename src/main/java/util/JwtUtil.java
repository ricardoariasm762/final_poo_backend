package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret:dev-secret-change-me-please-32-chars-min}")
    private String secret;

    @Value("${jwt.expiration-ms:3600000}")
    private long expirationMs; // 1 hora por defecto

    @Value("${jwt.refresh-expiration-ms:86400000}")
    private long refreshExpirationMs; // 24 horas por defecto

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generarToken(String usuario, String rol) {
        return Jwts.builder()
                .setSubject(usuario)
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generarRefreshToken(String usuario) {
        return Jwts.builder()
                .setSubject(usuario)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationMs))
                .claim("tipo", "refresh")
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean esTokenAccesoValido(String token) {
        Claims claims = obtenerClaims(token);
        if (claims == null) {
            return false;
        }
        Object tipo = claims.get("tipo");
        return tipo == null;
    }

    public boolean esRefreshTokenValido(String token) {
        Claims claims = obtenerClaims(token);
        if (claims == null) {
            return false;
        }
        Object tipo = claims.get("tipo");
        return "refresh".equals(tipo);
    }

    public String obtenerUsuarioDelToken(String token) {
        Claims claims = obtenerClaims(token);
        if (claims == null) {
            return null;
        }
        return claims.getSubject();
    }

    private Claims obtenerClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }
}
