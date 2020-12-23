package com.teamjw.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DeferredController {

	// Async 기반 RestTemplate -> 새로운 쓰레드를 생성하여 처리 하는 방법
	
	AsyncRestTemplate rt = new AsyncRestTemplate();
	
	static final String URL1 = "";
	static final String URL2 = "";
	
	@GetMapping("/rest")
	public DeferredResult<String> rest (int idx) {
		// 1. DeferredResult 를 사용하여, 비동기 처리후 반환 작업을 수행
		DeferredResult<String> dr = new DeferredResult<>();
		
		// 2. ListenableFuture 를 사용하여, Callback 처리 후, 다른 작업을 추가 할 수 있다.
		ListenableFuture<ResponseEntity<String>> f1 = rt.getForEntity("http://localhost:8081/service?req={req}", String.class, "helllo" + idx);
		// Callback 추가
		f1.addCallback(s -> {
			
		}, e-> {
			dr.setErrorResult(e.getMessage());
		});
		
		
		return dr;
		
	}
}
