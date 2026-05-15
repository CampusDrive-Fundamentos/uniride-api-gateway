package com.campusdrive.uniride;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // === IAM SERVICE ===
                .route("iam-auth", r -> r.path("/api/v1/auth/**")
                        .uri("http://iam-service:8081"))
                .route("iam-users", r -> r.path("/api/v1/users/**")
                        .uri("http://iam-service:8081"))
                .route("iam-swagger-ui", r -> r.path("/iam/swagger-ui/**", "/iam/v3/api-docs/**", "/iam/swagger-resources/**")
                        .filters(f -> f.rewritePath("/iam/(?<segment>.*)", "/${segment}"))
                        .uri("http://iam-service:8081"))
                .route("iam-swagger-html", r -> r.path("/iam/swagger-ui.html")
                        .filters(f -> f.rewritePath("/iam/swagger-ui.html", "/swagger-ui.html"))
                        .uri("http://iam-service:8081"))

                // === ROUTES SERVICE ===
                .route("routes-service", r -> r.path("/api/v1/routes/**")
                        .uri("http://routes-service:8082"))
                .route("routes-swagger-ui", r -> r.path("/routes/swagger-ui/**", "/routes/v3/api-docs/**", "/routes/swagger-resources/**")
                        .filters(f -> f.rewritePath("/routes/(?<segment>.*)", "/${segment}"))
                        .uri("http://routes-service:8082"))
                .route("routes-swagger-html", r -> r.path("/routes/swagger-ui.html")
                        .filters(f -> f.rewritePath("/routes/swagger-ui.html", "/swagger-ui.html"))
                        .uri("http://routes-service:8082"))

                // === BOOKING SERVICE ===
                .route("booking-service", r -> r.path("/api/v1/bookings/**")
                        .uri("http://booking-service:8083"))
                .route("booking-swagger-ui", r -> r.path("/bookings/swagger-ui/**", "/bookings/v3/api-docs/**", "/bookings/swagger-resources/**")
                        .filters(f -> f.rewritePath("/bookings/(?<segment>.*)", "/${segment}"))
                        .uri("http://booking-service:8083"))
                .route("booking-swagger-html", r -> r.path("/bookings/swagger-ui.html")
                        .filters(f -> f.rewritePath("/bookings/swagger-ui.html", "/swagger-ui.html"))
                        .uri("http://booking-service:8083"))

                .build();
    }
}
