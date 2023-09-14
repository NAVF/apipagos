package com.example.apipagos.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feigntLoggerLevel(){return Logger.Level.FULL;}
}
