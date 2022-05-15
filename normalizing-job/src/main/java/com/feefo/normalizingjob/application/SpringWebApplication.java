package com.feefo.normalizingjob.application;

import com.feefo.normalizingjob.config.AppProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages =
{
    "com.feefo.normalizingjob",
})
@ComponentScan(basePackages =
{
    "com.feefo.normalizingjob"
})
@EnableConfigurationProperties(AppProperties.class)
public class SpringWebApplication extends SpringBootServletInitializer
{

    public static void main (
        String[] args
    ) {
        SpringApplication
            .run(
                SpringWebApplication.class,
                args
            );
    }

    @Override
    protected SpringApplicationBuilder configure (
        SpringApplicationBuilder application
    ) {
        return application.sources(SpringWebApplication.class);
    }
}
