package com.kr.justin.hangplesajun.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
@SecurityScheme(
	name = "bearerAuth",
	type = SecuritySchemeType.HTTP,
	scheme = "bearer",
	bearerFormat = "JWT"
)

public class SwaggerConfig {
	@Bean
	public GroupedOpenApi authGroup() {
		return GroupedOpenApi.builder()
			.group("인증")
			.addOpenApiCustomizer(openApi -> openApi.info(new Info().title("항해플러스 사전스터디 API").version("0.0.1")))
			.packagesToScan("com.kr.justin.hangplesajun")
			.pathsToMatch("/api/auth/**")
			.build();
	}

	@Bean
	public GroupedOpenApi postGroup() {
		return GroupedOpenApi.builder()
			.group("게시판")
			.addOperationCustomizer((operation, handlerMethod) -> {
				operation.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
				return operation;
			})
			.addOpenApiCustomizer(openApi -> openApi.info(new Info().title("항해플러스 사전스터디 API").version("0.0.1")))
			.packagesToScan("com.kr.justin.hangplesajun")
			.pathsToMatch("/api/post*/**")
			.build();
	}


}
