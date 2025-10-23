package com.example.VEND.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("VEND API - Sistema de Vendas de Carros")
                        .version("1.0.0")
                        .description("""
                                API REST para gerenciamento de carros e usuários do sistema VEND.
                                
                                ## Funcionalidades principais:
                                * Cadastro e consulta de carros
                                * Gerenciamento de usuários administradores
                                * Busca de carros por marca e modelo
                                * Integração com API FIPE para consulta de preços
                                
                                ## Tecnologias utilizadas:
                                * Java 21
                                * Spring Boot 3.5.6
                                * PostgreSQL
                                * JPA/Hibernate
                                """)
                        .contact(new Contact()
                                .name("Equipe VEND")
                                .email("contato@vend.com.br")
                                .url("https://github.com/seu-usuario/vend"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desenvolvimento"),
                        new Server()
                                .url("https://api.vend.com.br")
                                .description("Servidor de Produção")
                ));
    }
}