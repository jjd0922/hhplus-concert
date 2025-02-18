package com.hanghe.common.configuration;

import com.hanghe.infrastructure.intercepter.QueueIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final QueueIntercepter queueIntercepter;

    public WebConfig(QueueIntercepter queueIntercepter) {
        this.queueIntercepter = queueIntercepter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(queueIntercepter)
                .addPathPatterns("/api/**");
    }
}
