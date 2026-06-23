package com.uniremington.parque_api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Se mantiene como Key tradicional de la versión 0.11.x
    private final Key key = Keys.hmacShaKeyFor(
            "uniremington_super_secret_key_123456789".getBytes()
    );

    public String generateToken(String email, String role) {
        // En la versión 0.11.x la sintaxis correcta es fluida usando 'setSubject' y 'signWith' con la llave directa
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key, SignatureAlgorithm.HS256) // Especificamos el algoritmo explícitamente
                .compact();
    }

    public Claims extractClaims(String token) {
        // En la versión 0.11.x se usa 'parserBuilder()' y 'setSigningKey()', lo cual remueve los warnings de deprecado
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public String getRole(String token) {
        return (String) extractClaims(token).get("role");
    }
}