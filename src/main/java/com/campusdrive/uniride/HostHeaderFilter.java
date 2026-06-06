package com.campusdrive.uniride;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class HostHeaderFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI routeUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        if (routeUri != null && routeUri.getHost() != null) {
            // Rewrite the Host header of the outbound request to match the target microservice host.
            // This is required for hosting platforms like Render/Heroku to route the request correctly.
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("Host", routeUri.getHost())
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // Must run after RouteToRequestUrlFilter (10000) so that GATEWAY_REQUEST_URL_ATTR is available,
        // but before NettyRoutingFilter / WebClientHttpRoutingFilter (Ordered.LOWEST_PRECEDENCE).
        return 10001;
    }
}
