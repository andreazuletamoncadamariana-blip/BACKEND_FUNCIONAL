package com.uniremington.parque_api.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniremington.parque_api.dto.LoginRequest;
import com.uniremington.parque_api.dto.LoginResponse;
import com.uniremington.parque_api.entity.Usuario;
import com.uniremington.parque_api.repository.UsuarioRepository;
import com.uniremington.parque_api.security.JwtUtil;

@Service
public class AuthService {

    private final UsuarioRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            UsuarioRepository repo,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {

        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {

        Usuario user = repo.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BadCredentialsException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new BadCredentialsException("Credenciales incorrectas");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRol().getNombre()
        );

        return new LoginResponse(
                token,
                user.getEmail(),
                user.getRol().getNombre()
        );
    }
}