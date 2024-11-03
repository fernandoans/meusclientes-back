package com.meucliente.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.meucliente.controller.AuthorizationFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthorizationFilter authorizationFilter;

    public WebConfig(AuthorizationFilter authorizationFilter) {
        this.authorizationFilter = authorizationFilter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationFilter);
    }
}