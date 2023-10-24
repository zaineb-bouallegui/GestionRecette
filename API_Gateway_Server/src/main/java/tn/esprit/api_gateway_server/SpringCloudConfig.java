package tn.esprit.api_gateway_server;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringCloudConfig {


    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
//Micro-service 1
                .route(r -> r.path("/Event/**")
                        .uri("http://localhost:8089/")).build();
    }
}