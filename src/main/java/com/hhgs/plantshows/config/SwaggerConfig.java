package com.hhgs.plantshows.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.hhgs.plantshows.controller")).paths(PathSelectors.any()).build();
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Plant-shows接口").description("场站信息展示接口").termsOfServiceUrl("http://localhost:8080/plant-shows/swagger-ui.html").version("1.0").build();
    }
}
