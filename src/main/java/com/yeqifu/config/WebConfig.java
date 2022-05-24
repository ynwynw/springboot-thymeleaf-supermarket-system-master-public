package com.yeqifu.config;

import com.yeqifu.interceptor.ControllerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public ControllerInterceptor getControllerInterceptor(){
        return new ControllerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getControllerInterceptor()).addPathPatterns("/**").excludePathPatterns("/resources/**");
    }


}