package com.dbr.generator.springboot.ws.config;

import com.dbr.generator.springboot.ws.system.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ApplicationProperties applicationProperties;

    @Bean
    public Docket swaggerSystemApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("System")
                .select()
                .apis(RequestHandlerSelectors.basePackage(ApplicationProperties.SYSTEM_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(" [ System ]"));
    }

    @Bean
    public Docket swaggerAppApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("App")
                .select()
                .apis(RequestHandlerSelectors.basePackage(ApplicationProperties.APP_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(" [ Application ]"));
    }

    // describe your apis
    private ApiInfo apiInfo(String titleSuffix) {
        return new ApiInfoBuilder()
                .title(applicationProperties.getName() + titleSuffix)
                .description(applicationProperties.getDescription())
                .version(applicationProperties.getVersion())
                .build();
    }

}