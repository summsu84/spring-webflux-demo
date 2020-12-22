package com.teamjw.runnable;

import java.time.LocalTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableExampleMain {

	static class MyRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String result = "Called at " + LocalTime.now();
			log.info("run : {} ", result);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		MyRunnable runnable = new MyRunnable();
		Thread thread = new Thread(runnable);
		thread.start();
		log.info("종료");
	}
	
}
