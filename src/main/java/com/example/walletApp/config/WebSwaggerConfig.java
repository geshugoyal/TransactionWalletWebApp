package com.example.walletApp.config;

import com.google.common.base.Predicates;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Data
public class WebSwaggerConfig {

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.termsOfServiceURL}")
    private String termsOfServiceURL;

    @Value("${swagger.license}")
    private String license;

    @Value("${swagger.licenseURL}")
    private String licenseURL;

    @Value("${swagger.version}")
    private String version;

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(initApiInfoBuilder())
                .select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"))) // Do not import APIs from this package
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud"))) // Do not import APIs from this package
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo initApiInfoBuilder() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceURL)
                .license(license)
                .licenseUrl(licenseURL)
                .version(version)
                .build();
    }

}
