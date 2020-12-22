package com.teamjw;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
//@EnableAsync
public class AsyncConfiguration {
//	@Bean
//	public Executor asyncThreadTaskExecutor() {
//		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//		//threadPoolTaskExecutor.setCorePoolSize(100);
//		//threadPoolTaskExecutor.setMaxPoolSize(100);
//		threadPoolTaskExecutor.setThreadNamePrefix("jeong-pro-pool");
//		return threadPoolTaskExecutor;
//	}
//	@Bean
//	public AsyncTaskExecutor taskExecutor() {
//		AsyncTaskExecutor t = new SimpleAsyncTaskExecutor();
//		return t;
//	}
}
