package com.lgcns.icst.mission.spring.thymeleaf.hangma.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

@Slf4j
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        UUID uuid = UUID.randomUUID();

        String contextPath = servletRequest.getContextPath();
        String requestURI = servletRequest.getRequestURI();
        final String[] blackList = {"/*.ico", "/css/*", "/js/*", "/img/*", "/lib/*"};
        if (!PatternMatchUtils.simpleMatch(blackList, requestURI.replace(contextPath, ""))) {
            log.info("LOG_FILTER[{}]: [{}] {}", uuid, servletRequest.getMethod(), requestURI);

            StringBuilder parameterString = null;
            Enumeration<String> parameterNames = servletRequest.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String parameterName = parameterNames.nextElement();
                String value = servletRequest.getParameter(parameterName);
                if (parameterString == null) {
                    parameterString = new StringBuilder();
                } else {
                    parameterString.append(";");
                }
                parameterString.append(parameterName).append("=").append(value);
            }
            if (parameterString != null) {
                log.debug("LOG_FILTER[{}]: ?{}", uuid, parameterString);
            }
        }
        chain.doFilter(request, response);
    }
}
