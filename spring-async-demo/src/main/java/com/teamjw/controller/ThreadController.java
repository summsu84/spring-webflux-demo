package com.teamjw.controller;

import java.util.concurrent.Callable;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ThreadController {

	@GetMapping("/thread")
	// Callable 을 사용하면, 서블릿 쓰레드를 가지고 있는 것이 아니라 요청 후 반납
	// 비동기적으로 처리하고, 서블릿 쓰레드는 요청 후 바로 반환
	public String async() throws InterruptedException {
		log.info("thread controller");
		ThreadClass th = new ThreadClass();
		Thread thread = new Thread(th);
		thread.start();
		return "async return";
	}
	

	public class ThreadClass implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			log.info("async");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
