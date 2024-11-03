package com.meucliente.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	
	// Documentação SWAGGER em: http://localhost:8080/meusclientes/swagger-ui/index.html#/

    @Bean
    protected OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Meus Clientes")
                                .version("1.0.0")
                                .description("Documentação da API para o sistema de clientes"));
    }

    @Bean
    protected GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToExclude("/emails/**", "/telefones/**", "/clientes/**", "/profile/**") // Excluir endpoints gerados automaticamente para a entidades
                .build();
    }
}