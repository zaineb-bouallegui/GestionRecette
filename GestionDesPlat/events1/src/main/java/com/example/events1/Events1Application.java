package com.example.events1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Events1Application {

    public static void main(String[] args) {
        SpringApplication.run(Events1Application.class, args);
    }

}
