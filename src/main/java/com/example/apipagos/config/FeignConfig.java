package com.example.apipagos.config;

import feign.Logger;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feigntLoggerLevel(){return Logger.Level.FULL;}

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            try{
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
                requestTemplate.header("Authorization", httpServletRequest.getHeader("Authorization"));
            }catch (Exception e){
                log.warn("No  thread-bound request found. Ex: {}", e.getMessage());
            }
        };
    }
}
