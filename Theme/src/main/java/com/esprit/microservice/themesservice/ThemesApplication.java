package com.esprit.microservice.themesservice;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
public class ThemesApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.esprit.microservice.themesservice.ThemesApplication.class, args);
	}
	
	/* @Bean
	    ApplicationRunner init(ThemeRepository repository) {
	        return args -> {
	            Stream.of("thermomix", "detox", "bebe","traditionnelle").forEach(titre -> {
	                repository.save(new Theme(titre));
	            });
	            repository.findAll().forEach(System.out::println);
	        };
	    }*/

}
