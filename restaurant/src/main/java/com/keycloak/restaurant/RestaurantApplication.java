package com.keycloak.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@SpringBootApplication
// @EnableSwagger2
@SecurityScheme(
	    name = "Keycloak"
	    , openIdConnectUrl = "http://localhost:8080/realms/admin-mohit/.well-known/openid-configuration"
	    , scheme = "bearer"
	    , type = SecuritySchemeType.OPENIDCONNECT
	    , in = SecuritySchemeIn.HEADER
	    )
public class RestaurantApplication {

	// @Bean
	// public Docket api() {
	// 	return new Docket(DocumentationType.SWAGGER_2).select()
	// 			.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	// 			.paths(PathSelectors.any())
	// 			.build()
	// 			.apiInfo(apiInfo())
	// 			.useDefaultResponseMessages(false);
	// }

	// @Bean
	// public ApiInfo apiInfo() {
	// 	final ApiInfoBuilder builder = new ApiInfoBuilder();
	// 	return builder.build();
	// }

	public static void main(String[] args) {
	SpringApplication.run(RestaurantApplication.class, args);
	}

}
