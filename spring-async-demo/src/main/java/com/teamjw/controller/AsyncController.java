package com.teamjw.controller;

import java.util.concurrent.Callable;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AsyncController {

	@GetMapping("/async")
	// Callable 을 사용하면, 서블릿 쓰레드를 가지고 있는 것이 아니라 요청 후 반납
	// 비동기적으로 처리하고, 서블릿 쓰레드는 요청 후 바로 반환
	public String async() throws InterruptedException {
		log.info("async controller");
		this.callAyncService();
		return "async return";
	}
	
	@Async
	public void callAyncService() throws InterruptedException {
		log.info("async");
		Thread.sleep(2000);
	}
}
