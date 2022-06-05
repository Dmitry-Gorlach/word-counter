package com.synechron.wordcounter.poc.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger")
public class WordCounterSwaggerFoxConfig {

  public static final String AUTHORIZATION_HEADER = "Authorization";

  private String title;
  private String description;
  private String version;
  private String termsOfServiceUrl;
  private String license;
  private String licenseUrl;
  private String contactName;
  private String contactUrl;
  private String contactEmail;

  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.example.wordcounter"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())
        .securityContexts(List.of(securityContext()))
        .securitySchemes(List.of(apiKey()));
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        this.title,
        this.description,
        this.version,
        this.termsOfServiceUrl,
        new Contact(this.contactName, this.contactUrl, this.contactEmail),
        this.license,
        this.licenseUrl,
        Collections.emptyList());
  }

  private ApiKey apiKey() {
    return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return List.of(new SecurityReference("JWT", authorizationScopes));
  }
}
