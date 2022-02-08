package com.lgcns.icst.mission.spring.thymeleaf.hangma.common.config;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.common.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("", "/index.html", "/member/login", "/member/logout", "/member/sign-up",
                        "/js/**", "/css/**", "/lib/**", "/img/**", "/**.ico");
    }
}
