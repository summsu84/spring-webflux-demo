package com.teamjw.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefferedController {

	@GetMapping("/emitter")
	// Callable 을 사용하면, 서블릿 쓰레드를 가지고 있는 것이 아니라 요청 후 반납
	// 비동기적으로 처리하고, 서블릿 쓰레드는 요청 후 바로 반환
	public ResponseBodyEmitter emitter() throws InterruptedException {
		log.info("emitter");
		ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		
		Executors.newSingleThreadExecutor().submit(() -> {
			try {
				for(int i = 1 ; i <= 20 ; i++) {
					emitter.send("<p>Steram" + i + "</p>");
					Thread.sleep(100);
				}
			} catch (Exception e) {
				
			}
		});
		return emitter;
	}
}
