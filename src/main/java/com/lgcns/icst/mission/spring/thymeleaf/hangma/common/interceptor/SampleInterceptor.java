package com.lgcns.icst.mission.spring.thymeleaf.hangma.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 컨트롤러(정확히는 핸들러 어댑터) 호출 전에 호출 된다.
        return true;    // true 면 계속 진행, false 면 진행을 멈춘다.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 컨트롤러(핸들러 어댑터) 호출 후에 호출 된다.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 뷰가 랜더링 된 이후에 호출된다.
        // 에러가 발생 해도 호출된다.
    }
}
