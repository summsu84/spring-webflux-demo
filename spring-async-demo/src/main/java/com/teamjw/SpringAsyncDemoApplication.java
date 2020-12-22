package com.teamjw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringAsyncDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAsyncDemoApplication.class, args);
	}

}
