package com.uniremington.parque_api.service;

import com.uniremington.parque_api.repository.UsuarioRepository;
import com.uniremington.parque_api.entity.Usuario;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repo;

    public CustomUserDetailsService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario u = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user"));

        return new org.springframework.security.core.userdetails.User(
                u.getEmail(),
                u.getPassword(),
                Collections.singleton(() -> "ROLE_" + u.getRol().getNombre())
        );
    }
}