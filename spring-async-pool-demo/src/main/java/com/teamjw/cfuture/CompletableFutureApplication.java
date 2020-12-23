package com.teamjw.cfuture;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import com.teamjw.remote.RemoteDemoApplication;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
public class CompletableFutureApplication {

	// CompletableFuture를 이용한 비동기 처리 컨트롤러
	@RestController
	public static class CFutureController {
		
		@Autowired MyService myService;
		AsyncRestTemplate rt = new AsyncRestTemplate(new Netty4ClientHttpRequestFactory(new NioEventLoopGroup(1)));
		// AsycRestTemplate 는 별도의 쓰레드를 생성하여 비동기 처리 하는 구조. 여러개의 요청이 들어오면 요청 개수만큼의 쓰레드가 생성되어 처리된다.
		//AsyncRestTemplate rt = new AsyncRestTemplate();
		
		static final String URL1 = "http://localhost:8082/service?req={req}";
		static final String URL2 = "http://localhost:8082/service2?req={req}";
		
		@GetMapping("/cfuture/rest")
		public DeferredResult<String> rest(int idx) {
			// 비동기 처리 후 결과를 담아서, 유휴 서블릿 쓰레드를 통해서 결과를 반환하는 구조
			DeferredResult<String> dr = new DeferredResult<>();
			
			// 첫번째 비동기 작업
			toCF(rt.getForEntity(URL1,  String.class, "h" + idx))
			.thenCompose(s -> toCF(rt.getForEntity(URL2,  String.class, s.getBody())))
			.thenApplyAsync(s2 -> myService.work(s2.getBody()))
			.thenAccept(s3 -> dr.setResult(s3))
			.exceptionally(e -> {dr.setErrorResult(e.getMessage()); return (Void)null;});

			return dr;
		}
		
		<T> CompletableFuture<T> toCF(ListenableFuture<T> lf) {
			// CompletableFuture는 명시적으로 비동기작업이 완료 되었을때 쓸수 있다.
			CompletableFuture<T> cf = new CompletableFuture<T>();
			
			lf.addCallback(s-> {cf.complete(s);}, e-> {cf.completeExceptionally(e);});
			
			
			return cf;
		}
	}
	
	@Service
	public static class MyService {
		
		public String work(String req) {
			return req + "/asyncwork";
		}
	}
	
	@Bean
	public ThreadPoolTaskExecutor myThreadPool() {
		ThreadPoolTaskExecutor te = new ThreadPoolTaskExecutor();
		te.setCorePoolSize(1);
		te.setMaxPoolSize(1);
		te.initialize();
		return te;
	}
	
	public static void main(String[] args) {
		System.setProperty("server.tomcat.max-threads", "1");
		SpringApplication.run(CompletableFutureApplication.class, args);
	}
}
