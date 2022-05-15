package com.feefo.normalizingjob.application;

import java.time.LocalDate;
import java.util.Arrays;

import com.feefo.normalizingjob.dto.ResponseBody;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ComponentScan
public class SwaggerConfig implements WebMvcConfigurer
{

    @Value("${build.version}")
    private String version;

    @Value("${swagger.enabled:false}")
    private boolean enableSwagger;

    @Bean
    public Docket api () {
        return new Docket(DocumentationType.SWAGGER_2)
            .enable(enableSwagger)
            .apiInfo(apiInfo())
            .select()
            .apis(
                RequestHandlerSelectors
                    .basePackage("com.feefo.normalizingjob")
            )
            .paths(PathSelectors.any())
            .build()
            .pathMapping("/")
            .directModelSubstitute(
                LocalDate.class,
                String.class
            )
            // .securitySchemes(Arrays.asList(new SecurityScheme[]{
            // }))
            .genericModelSubstitutes(ResponseBody.class)
            .useDefaultResponseMessages(false)
            .globalResponses(
                HttpMethod.GET,
                Arrays.asList(new Response[]
                {
                    new ResponseBuilder()
                        .code("500")
                        .description(
                            "Internal Server Error, the service cannot process the request"
                        )
                        .build(),
                    new ResponseBuilder()
                        .code("401")
                        .description(
                            "You are not authorized to access this resource"
                        )
                        .build(),
                    new ResponseBuilder()
                        .code("412")
                        .description(
                            "Precondition Failed, the request cannot be processed"
                        )
                        .build()
                })
            );
    }

    private ApiInfo apiInfo () {
        return new ApiInfoBuilder()
            .title("Feefo Normalizing Job")
            .description(
                "This is the Feefo Normalizing Job API. "
            )
            .version(version)
            .termsOfServiceUrl("http://example.com.br")
            .license("LICENSE")
            .licenseUrl("http://example.com.br")
            .build();
    }

    @Override
    public void addResourceHandlers (
        ResourceHandlerRegistry registry
    ) {

        registry
            .addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

        registry
            .addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
