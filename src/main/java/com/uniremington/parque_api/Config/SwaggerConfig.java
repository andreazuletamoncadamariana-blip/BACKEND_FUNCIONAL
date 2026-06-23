package com.uniremington.parque_api.Config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Uniremington al Parque API")
                        .version("1.0")
                        .description("API oficial del sistema de impacto social"));
    }
}