package com.uniremington.parque_api.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}