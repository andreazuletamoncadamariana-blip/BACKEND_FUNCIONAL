package com.uniremington.parque_api.controller;

import com.uniremington.parque_api.dto.LoginRequest;
import com.uniremington.parque_api.dto.LoginResponse;
import com.uniremington.parque_api.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }
}