package com.teamjw.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RestTemplateController {

	
	RestTemplate rt = new RestTemplate();
	
	static final String URL1 = "";
	static final String URL2 = "";
	
	@GetMapping("/rest/rt")
	public String rest (int idx) {
		String res = rt.getForObject("http://localhost:8082/service?req={req|}", String.class, "hello" + idx);
		return "rest" + idx;
		
	}
}
