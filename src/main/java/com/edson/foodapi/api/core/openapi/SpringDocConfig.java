package com.edson.foodapi.api.core.openapi;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    
    @Bean
    public OpenAPI springDocApi() {
        return new OpenAPI()
                .info(new Info().title("Food Api")
                        .description("Api de restaurantes")
                        .version("v0.0.1"));
    }
}
