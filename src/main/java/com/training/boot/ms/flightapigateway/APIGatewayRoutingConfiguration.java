package com.training.boot.ms.flightapigateway;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayRoutingConfiguration {


	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		Function<PredicateSpec, Buildable<Route>> routeFunc1 = (p) -> 
			 p.path("/get")
			.uri("http://httpbin.org:80");
			 
			 //on api gateway - path http://localhost:8765/api/v1/from/{from}/to/{to}
			 //uri to send the request - http://localhost:8765/currency-conversion-service/api/v1/from/{from}/to/{to}
		Function<PredicateSpec, Buildable<Route>> routeFunc2 = (p) -> 
			 p.path("/api/v1/from/{from}/to/{to}")
			.uri("lb://currency-conversion-service/**");
			 
		Function<PredicateSpec, Buildable<Route>> routeFunc3 = (p) -> 
			 p.path("/currency/converter/from/{from}/to/{to}")
			 .filters(f -> f.rewritePath("/currency/converter/(?<segment>.*)", "/api/v1/${segment}"))
			.uri("lb://currency-conversion-service/");
		

		return builder.routes()
				.route(routeFunc1)
				.route(routeFunc2)
				.route(routeFunc3)
				.build();
	}
	
}

