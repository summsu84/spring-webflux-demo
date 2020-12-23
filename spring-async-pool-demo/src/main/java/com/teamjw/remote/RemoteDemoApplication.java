package com.teamjw.remote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamjw.SpringAsyncPoolDemoApplication;

// Remote 서비스 어플리케이션
@SpringBootApplication
public class RemoteDemoApplication {

	@RestController
	public static class MyControlelr {
		@GetMapping("/service") 
		public String service(String req) throws InterruptedException
		{
			
			Thread.sleep(2000);
			return req + "/service";
		}
		
		@GetMapping("/service2") 
		public String service2(String req) throws InterruptedException
		{
			
			Thread.sleep(2000);
			return req + "/service2";
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("server.port", "8082");
		System.setProperty("server.tomcat.max-threads", "1000");
		SpringApplication.run(RemoteDemoApplication.class, args);
	}

}
