package com.uniremington.parque_api.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniremington.parque_api.entity.Usuario;
import com.uniremington.parque_api.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(
            UsuarioRepository repo,
            PasswordEncoder passwordEncoder) {

        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return repo.findAll();
    }

    @GetMapping("/profesores")
    public List<Usuario> listarProfesores() {
        return repo.findByRolNombre("PROFESOR");
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {

        usuario.setPassword(
                passwordEncoder.encode(usuario.getPassword())
        );

        return repo.save(usuario);
    }
}