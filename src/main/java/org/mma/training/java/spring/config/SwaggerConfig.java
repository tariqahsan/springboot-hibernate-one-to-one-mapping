package org.mma.training.java.spring.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.mma.training.java.spring"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
                
    }
  
    //.apis(RequestHandlerSelectors.any())
	//.paths(PathSelectors.any())
    
    private ApiInfo apiDetails() {
    	return new ApiInfo (
                "Spring Boot One To One Mapping",
                "A demo Spring REST API using One-to-One Hibernate Mapping",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Tariq Ahsan", "http://mma.org", "tariqahsan@yahoo.com"),
                "License of API",
                "http://mma.org",
        		Collections.emptyList());
    }
}
