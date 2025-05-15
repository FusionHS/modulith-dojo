package com.fusionhs.modulithdojo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pizzaDeliveryOpenAPI() {
        Server devServer = new Server()
                .url("http://localhost:8080")
                .description("Development server");

        Info info = new Info()
                .title("Bob's Pizzas Employee API")
                .version("1.0.0")
                .description("""
                        API documentation for the Bob's Pizzas Delivery System.
                        This modular monolith demonstrates Spring Modulith architecture with:
                        - Employee task management
                        """);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
} 