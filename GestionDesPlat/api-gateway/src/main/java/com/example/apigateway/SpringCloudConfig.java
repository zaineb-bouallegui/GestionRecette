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
                        .uri("http://plat:8082")
                )
                .route("planifications_route", r -> r
                        .path("/api/planifications/**")
                        .uri("http://plat:8082")
                )
                .route("theme_route", r -> r
                        .path("/api/themes/**")
                        .uri("http://theme:8088")
                )
                .route("recette_route", r -> r
                        .path("/ms/recette/**")
                        .uri("http://recette:8086")
                )
                .route("ingrediants_route", r -> r
                        .path("/api/ingrediants/**")
                        .uri("http://ingrediants:8087")
                )
                .route("profile_route" ,r -> r
                        .path("/api/profile/**")
                        .uri("http://profile:8090")
                )
                .build();
    }

}
