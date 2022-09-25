package com.yumyapps.fakeoperator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FakeOperatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakeOperatorApplication.class, args);
    }


    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*/").allowedOrigins("http://localhost/8080");
            }
        };
    }


}

