package com.blog.web.config;

import com.blog.web.iInterceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class WebConfig {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new LoginInterceptor()
//                .addPathPatterns("/admin/**")
//                .excludePathPatterns("/adminn")
//                .excludePathPatterns("/admin/login"));
//    }

}
