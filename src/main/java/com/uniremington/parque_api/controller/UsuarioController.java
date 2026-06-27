package com.uniremington.parque_api.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniremington.parque_api.entity.Rol;
import com.uniremington.parque_api.entity.Usuario;
import com.uniremington.parque_api.repository.RolRepository;
import com.uniremington.parque_api.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioRepository repo;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(
            UsuarioRepository repo,
            RolRepository rolRepository,
            PasswordEncoder passwordEncoder) {

        this.repo = repo;
        this.rolRepository = rolRepository;
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

    @PostMapping("/registrar-docente")
    public Usuario registrarDocente(@RequestBody Usuario usuario) {

        Rol rolProfesor = rolRepository
                .findByNombre("PROFESOR")
                .orElseThrow(() ->
                        new RuntimeException("Rol PROFESOR no existe"));

        usuario.setPassword(
                passwordEncoder.encode(usuario.getPassword())
        );

        usuario.setRol(rolProfesor);

        return repo.save(usuario);
    }
}