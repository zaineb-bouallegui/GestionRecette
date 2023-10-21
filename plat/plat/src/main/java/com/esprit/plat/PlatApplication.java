package com.esprit.plat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PlatApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatApplication.class, args);
	}

}
