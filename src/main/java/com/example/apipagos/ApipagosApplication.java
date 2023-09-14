package com.example.apipagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApipagosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApipagosApplication.class, args);
	}

}
