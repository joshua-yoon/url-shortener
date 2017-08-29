package com.kotelking.shorten.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Code Annotations:
 * - https://github.com/swagger-api/swagger-core/wiki/Annotations
 *
 * SpringFox Documents:
 * - https://springfox.github.io/springfox/docs/current/
 *
 * Swagger Docs:
 * - http://swagger.io/docs/
 * - https://github.com/swagger-api/swagger-ui/tree/2.x
 */
@Configuration
@EnableSwagger2
@EnableWebMvc // Spring-boot doesn't need this.
@ComponentScan("com.kotelking.shorten.controller")
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kotelking.shorten.controller"))
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Short Url Geneartor")
                .description("Short Url Generator")
                .version("0.0.1")
                .license("MIT")
                .build();
    }
}
