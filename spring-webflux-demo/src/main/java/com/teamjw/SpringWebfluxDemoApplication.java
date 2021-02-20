package com.teamjw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class SpringWebfluxDemoApplication {

	
	WebClient client = WebClient.create();
	
	static final String URL1 = "http://localhost:8082/service?req={req}";
	static final String URL2 = "http://localhost:8082/service2?req={req}";
	
	@GetMapping("/rest")
	public Mono<String> rest(int idx) {
		// Mono<ClientResponse>
		return null;
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxDemoApplication.class, args);
	}

}
