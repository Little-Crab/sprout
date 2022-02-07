package com.pjf.server.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pjf
 * 类名：SwaggerConfig
 * 创建时间: 2022/2/2 16:18.
 */
@Configuration
@OpenAPIDefinition
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "Bearer"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {


        return new OpenAPI()
                .info(new Info().title("禾苗")
                        .description("记账+清单+博客")
                        .version("v0.0.1")
                        .license(new License().name("pjf").url("http://localhost:8081/doc.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("禾苗纪事参考接口")
                        .url("https://springshop.wiki.github.org/docs"));
    }

}
