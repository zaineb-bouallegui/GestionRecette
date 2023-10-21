package com.example.ms_gestionrecette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsGestionRecetteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsGestionRecetteApplication.class, args);
    }

}
