package com.DkGroup.jobFinder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Component
public class AppConfig extends WebMvcConfigurerAdapter{
	  @Autowired
	   ProductServiceInterceptor productServiceInterceptor;
	  @Autowired
	  logger myLogger;
	  @Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(productServiceInterceptor);
	      registry.addInterceptor(myLogger);
	   }
}
