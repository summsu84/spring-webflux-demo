package com.teamjw.cfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CFutureDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// ThreadPool 생성
		ExecutorService es = Executors.newFixedThreadPool(10);
		
		// 새로운 쓰레드로 실행하기 위해서는 ThreadPool 을 인자로 넣어져야 한다.
		CompletableFuture.supplyAsync(() -> {
			log.info("runAsync");
			return 1;
		}, es)
		.thenCompose(s -> {
			log.info("thenApply {}", s);
			return CompletableFuture.completedFuture(s + 1);
		})
		.thenApplyAsync(s2 -> {
			log.info("thenApply {}", s2);
			return s2 * 3;
		}, es)
		.exceptionally(e -> -10)
		.thenAcceptAsync(s3 -> log.info("thenAccept {}", s3), es);
		
		log.info("exit");
		
		ForkJoinPool.commonPool().shutdown();
		ForkJoinPool.commonPool().awaitTermination(10,  TimeUnit.SECONDS);
	}

}
