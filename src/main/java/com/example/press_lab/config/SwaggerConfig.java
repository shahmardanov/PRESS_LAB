package com.example.press_lab.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addParameters("Accept-Language",
                        new Parameter()
                                .in("header")
                                .name("Accept-Language")
                                .description("Dil seçimi")
                                .required(false)
                                .schema(new io.swagger.v3.oas.models.media.StringSchema()._default("az"))))
                .info(new Info().title("API").version("1.0"));
    }

}
