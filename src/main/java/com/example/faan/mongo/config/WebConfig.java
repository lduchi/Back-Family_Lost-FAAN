package com.example.faan.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://viaticos.gesinsoft.com", "http://localhost:3000","http://localhost:4200","http://10.0.2.2:8080")
        //        .allowedOrigins("https://server.gesinsoft.com:11443/")//QUITAR CON EL SWAGGER
                .allowedMethods("Access-Control-Allow-Methods","GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type","*")
                .exposedHeaders("Authorization")
                .allowCredentials(true);
    }

    @Bean(name = "MiWebRequestInterceptor")
    public WebRequestInterceptor webRequestInterceptor() {
        return new MiWebRequestInterceptor();
    }






}


