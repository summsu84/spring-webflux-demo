package com.teamjw.callable;

import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CallableExampleMain {

	static class MyCallable implements Callable<String> {

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			String result = "Called at " + LocalTime.now();
			log.info("run : {} ", result);
			Thread.sleep(3000);
			return result;
		}
	}
	
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable callable = new MyCallable();
        FutureTask futureTask = new FutureTask(callable);
        Thread thread = new Thread(futureTask);
        thread.start();

        // 결과가 리턴되기를 기다립니다.
        log.info("result : " + futureTask.get());
    }
}
