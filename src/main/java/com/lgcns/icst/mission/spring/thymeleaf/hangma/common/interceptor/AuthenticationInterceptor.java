package com.lgcns.icst.mission.spring.thymeleaf.hangma.common.interceptor;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.common.constant.SessionKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionKey.EMP_NO) == null) {
            String requestURI = request.getRequestURI();
            String redirectURI = requestURI.replace(request.getContextPath(), "");

            log.warn("인증되지 않은 사용자입니다. ({})", requestURI);
            response.sendRedirect(request.getContextPath() + "/member/login?redirectURI=" + redirectURI);
            return false;
        }
        return true;
    }
}
