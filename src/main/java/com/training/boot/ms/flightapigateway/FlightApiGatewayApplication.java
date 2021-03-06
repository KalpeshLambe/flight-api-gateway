package com.training.boot.ms.flightapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FlightApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightApiGatewayApplication.class, args);
	}

}
