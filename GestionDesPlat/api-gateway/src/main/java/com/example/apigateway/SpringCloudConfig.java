package com.example.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("plats_route", r -> r
                        .path("/api/plats/**")
                        .uri("http://localhost:8088")
                )
                .route("planifications_route", r -> r
                        .path("/api/planifications/**")
                        .uri("http://localhost:8088")
                )
                .build();
    }

}
