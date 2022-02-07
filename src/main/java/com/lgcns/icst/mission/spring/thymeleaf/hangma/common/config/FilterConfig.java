package com.lgcns.icst.mission.spring.thymeleaf.hangma.common.config;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.common.filter.SampleFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

@Configuration
public class FilterConfig {

//    @Bean   // 실제 등록할 때는 주석을 해제
    public FilterRegistrationBean<SampleFilter> loggingFilter() {
        FilterRegistrationBean<SampleFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new SampleFilter());
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("*");
        return filterFilterRegistrationBean;
    }
}
