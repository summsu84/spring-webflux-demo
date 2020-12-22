package com.teamjw.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamjw.service.AsyncService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AsyncController {

	@Autowired
	private AsyncService asyncService;
	
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/async")
	// Callable 을 사용하면, 서블릿 쓰레드를 가지고 있는 것이 아니라 요청 후 반납
	// 비동기적으로 처리하고, 서블릿 쓰레드는 요청 후 바로 반환
	public String async() throws InterruptedException {
		log.info("async controller");
		
		asyncService.method1();
		
		return "async return";
	}
	
	@GetMapping("/future/async")
	// Callable 을 사용하면, 서블릿 쓰레드를 가지고 있는 것이 아니라 요청 후 반납
	// 비동기적으로 처리하고, 서블릿 쓰레드는 요청 후 바로 반환
	public String futureAsync() throws InterruptedException, ExecutionException {
		log.info("async controller");
		
		Future<String> fs = asyncService.methodFuture1();
		Future<String> fs2 = asyncService.methodFuture2();
		
		String result = fs.get();
		String result2 = fs2.get();
		
		return "async return : " + result + result2;
	}
	
	@GetMapping("/sync")
	// Callable 을 사용하면, 서블릿 쓰레드를 가지고 있는 것이 아니라 요청 후 반납
	// 비동기적으로 처리하고, 서블릿 쓰레드는 요청 후 바로 반환
	public String sync() throws InterruptedException, ExecutionException {
		log.info("async controller");
		
		String fs = asyncService.methodSync1();
		String fs2 = asyncService.methodSync2();
		
		
		return "async return : " + fs + fs2;
	}
}
