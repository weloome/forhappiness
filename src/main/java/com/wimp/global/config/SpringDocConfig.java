package com.wimp.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SpringDocConfig {

    String VERSION = "v1.0.0";
    String TITLE = "wimp API";
    String DESCRIPTION = "wimp API Document";

    String BEARER_FORMAT = "JWT";

    static String SCHEME_HEADER = "Authorization";
    static String SCHEME_NAME = "bearer";


    @Bean
    public OpenAPI apiInfo() {

        Info info = new Info()
                .version(VERSION)
                .title(TITLE)
                .description(DESCRIPTION);

        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(SCHEME_HEADER);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(SCHEME_HEADER, new SecurityScheme()
                        .name(SCHEME_HEADER)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme(SCHEME_NAME)
                        .bearerFormat(BEARER_FORMAT).in(SecurityScheme.In.HEADER)); // 토큰 형식을 지정하는 임의의 문자(Optional)

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }

    @Bean
    @Primary
    public GroupedOpenApi webApi() {
        return GroupedOpenApi.builder()
                .group("service")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/admin/**")
                .build();
    }
}