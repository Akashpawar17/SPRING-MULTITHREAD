package com.scheduling;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class FixedDelayScheduler {
	@Scheduled(fixedDelay = 5000)
	public void run() throws InterruptedException {
		System.out.println("Fixed delay scheduler is running at " + new Date());
		Thread.sleep(3000);
	}

}
