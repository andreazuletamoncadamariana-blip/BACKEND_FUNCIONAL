package com.uniremington.parque_api.security;

import com.uniremington.parque_api.entity.Rol;
import com.uniremington.parque_api.entity.Usuario;
import com.uniremington.parque_api.repository.RolRepository;
import com.uniremington.parque_api.repository.UsuarioRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth

                // LOGIN
                .requestMatchers("/auth/login/").permitAll()

                // JORNADA
                .requestMatchers("/jornadas/**").permitAll()


                // USUARIOS Y DOCENTES
                .requestMatchers("/usuarios/**").permitAll()

                // DONACIONES
                .requestMatchers("/api/donaciones/**").permitAll()

                // ESTUDIANTES
                .requestMatchers("/estudiantes/**").permitAll()

                // SERVICIOS
                .requestMatchers("/servicios/**").permitAll()

                // SEGUIMIENTO
                .requestMatchers("/seguimiento/**").permitAll()

                // SWAGGER
                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()

                // PREFLIGHT CORS
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // SOLO ADMINISTRADOR
                .requestMatchers("/recursos/**")
                .hasAuthority("ROLE_ADMINISTRADOR")

                // TODO LO DEMÁS REQUIERE TOKEN
                .anyRequest().authenticated()
        );

        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:4200")
        );

        configuration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        configuration.setExposedHeaders(
                List.of("Authorization")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

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
                        nuevoRol.setId(1L);
                        nuevoRol.setNombre("ADMINISTRADOR");

                        return rolRepo.save(nuevoRol);
                    });

            if (usuarioRepo.findByEmail("admin@uniremington.edu.co").isEmpty()) {

                Usuario admin = new Usuario();

                admin.setNombre("Administrador Sistema");
                admin.setEmail("admin@uniremington.edu.co");
                admin.setPassword(
                        passwordEncoder.encode("admin123")
                );

                admin.setRol(rolAdmin);

                usuarioRepo.save(admin);
            }
        };
    }
}