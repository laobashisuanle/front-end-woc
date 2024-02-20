package com.example.mybetis_plus_demo.config;

import com.example.mybetis_plus_demo.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor())   //配置拦截器规则
                .addPathPatterns("/**") ;   //拦截所有请求路径
        super.addInterceptors(registry);
    }

    

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

}
