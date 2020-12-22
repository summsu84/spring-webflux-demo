package com.teamjw.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {

	
	@Async("threadPoolTaskExecutor")
    public void method1() {
        log.info("method 1-1");
        try {
            Thread.sleep(4000);
        }catch(Exception ex){
        	log.error("",ex);
        }
        log.info("method 1-2");
    }

    @Async("threadPoolTaskExecutor")
    public void method2() {
    	log.info("method 2-1");
        method3();
        log.info("method 2-2");
    }

    @Async("threadPoolTaskExecutor")
    public void method3() {
    	log.info("method 3-1");
        try {
        	Thread.sleep(1000);
        }catch(Exception ex){
        	log.error("",ex);
        }
        log.info("method 3-2");
    }
    
    
    @Async("threadPoolTaskExecutor")
    public Future<String> methodFuture1() {
        log.info("method 1-1");
        try {
            Thread.sleep(4000);
        }catch(Exception ex){
        	log.error("",ex);
        }
        log.info("method 1-2");
        
        return new AsyncResult<String>("AsyncFutureSuccess");
    }
    @Async("threadPoolTaskExecutor")
    public Future<String> methodFuture2() {
        log.info("method 1-1");
        try {
            Thread.sleep(6000);
        }catch(Exception ex){
        	log.error("",ex);
        }
        log.info("method 1-2");
        
        return new AsyncResult<String>("AsyncFutureSuccess2");
    }
    
    public String methodSync1() {
        log.info("method 1-1");
        try {
            Thread.sleep(4000);
        }catch(Exception ex){
        	log.error("",ex);
        }
        log.info("method 1-2");
        
        return new String("AsyncFutureSuccess");
    }
    public String methodSync2() {
        log.info("method 1-1");
        try {
            Thread.sleep(6000);
        }catch(Exception ex){
        	log.error("",ex);
        }
        log.info("method 1-2");
        
        return new String("AsyncFutureSuccess2");
    }
}
