package com.uniremington.parque_api.security;

import com.uniremington.parque_api.entity.Usuario;
import com.uniremington.parque_api.entity.Rol;
import com.uniremington.parque_api.repository.UsuarioRepository;
import com.uniremington.parque_api.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(csrf -> csrf.disable())
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.authorizeHttpRequests(auth -> auth
        // 1. Permite explícitamente las rutas públicas ANTES de cualquier otra regla
        .requestMatchers("/estudiantes/**", "/servicios/**", "/seguimiento/**").permitAll()
        .requestMatchers("/auth/**").permitAll()
        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
        
        // 2. Rutas protegidas
        .requestMatchers("/usuarios/**").permitAll()
        .requestMatchers("/recursos/**").hasAuthority("ROLE_ADMINISTRADOR")
        
        .anyRequest().authenticated()
    );

    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); 
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); 
        return source;
    }

    @Bean
    public CommandLineRunner initDatabase(
            UsuarioRepository usuarioRepo, 
            RolRepository rolRepo, 
            PasswordEncoder passwordEncoder) {
        return args -> {
            Rol rolAdmin = rolRepo.findByNombre("ADMINISTRADOR")
                    .orElseGet(() -> {
                        Rol nuevoRol = new Rol();
                        nuevoRol.setId(1l); 
                        nuevoRol.setNombre("ADMINISTRADOR");
                        return rolRepo.save(nuevoRol); 
                    });

            if (usuarioRepo.findByEmail("admin@uniremington.edu.co").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador Sistema");
                admin.setEmail("admin@uniremington.edu.co");
                admin.setPassword(passwordEncoder.encode("admin123")); 
                admin.setRol(rolAdmin);
                usuarioRepo.save(admin);
            }
        };
    }
}