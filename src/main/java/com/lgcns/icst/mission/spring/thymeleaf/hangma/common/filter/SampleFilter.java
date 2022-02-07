package com.lgcns.icst.mission.spring.thymeleaf.hangma.common.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class SampleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 공통 로직

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            //  여기서 잡힘
        }
    }
}
