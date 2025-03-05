package com.teachmeskills.market.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("log_views_interceptor")
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        logger.info("Request URL -> {}", request.getRequestURL());
        logger.info("Method -> {}", request.getMethod());
        logger.info("Remote Address -> {}", request.getRemoteAddr());
        System.out.println("Request URL -> " + request.getRequestURL());
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView){
        logger.info("Response Status -> {}", response.getStatus());
        System.out.println("Response Status -> " + response.getStatus());
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler, Exception ex){
        if (ex != null) {
            logger.error("Request failed with exception -> {}", ex.getMessage());
            System.out.println("Request failed with exception -> {" + ex.getMessage());
        }
        logger.info("Request completed for URL -> {}", request.getRequestURL());
        System.out.println("Request completed for URL -> " + request.getRequestURL());
    }
}