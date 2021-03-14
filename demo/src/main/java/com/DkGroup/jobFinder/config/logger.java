package com.DkGroup.jobFinder.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class logger 
  extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Object handler) {
        System.out.println(request.getMethod()+"->"+ request.getRequestURI());
      
       
        return true;

    }

    @Override
    public void afterCompletion(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Object handler, 
      Exception ex) {
        //
    }
}
