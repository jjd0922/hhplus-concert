package com.hanghe.common.configuration;

import com.hanghe.infrastructure.intercepter.TokenIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TokenIntercepter tokenIntercepter;

    public WebConfig(TokenIntercepter tokenIntercepter) {
        this.tokenIntercepter = tokenIntercepter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenIntercepter)
                .addPathPatterns("/api/**");
    }
}
