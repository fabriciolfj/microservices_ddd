package br.com.spark.gatewaynew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GatewayNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayNewApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalanceWebClientBuilder() {
		final WebClient.Builder builder = WebClient.builder();
		return builder;
	}


}
