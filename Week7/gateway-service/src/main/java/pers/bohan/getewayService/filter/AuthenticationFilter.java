package pers.bohan.getewayService.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import pers.bohan.getewayService.jwt.JwtUtils;
import reactor.core.publisher.Mono;

public class AuthenticationFilter implements GlobalFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return unauthorizedResponse(exchange);
        }
        token = token.substring(7);
        if (!jwtUtils.validateJwtToken(token)) {
            return unauthorizedResponse(exchange);
        }
        String username = jwtUtils.getUserNameFromJwtToken(token);
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header("X-User-Name", username)
                .build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

}
