package com.tec.rateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {
	final RateLimiter rateLimiter = RateLimiter.create(1);
	public static void main(String[] args) {
		for (int i = 0; i < 500000; i++) {
			RateLimiterTest rateLimiterTest = new RateLimiterTest();
			rateLimiterTest.callRateLimiter(i);
//			rateLimiterTest.testAcquire();
		}
	}
	private void callRateLimiter(int i) {
		System.out.println("等待 ：" + rateLimiter.acquire());
		if(!rateLimiter.tryAcquire()){
			System.out.println(i + " 限流！ "+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		}
	}

	public void testAcquire() {
		RateLimiter limiter = RateLimiter.create(1);
		for (int i = 1; i < 10; i = i + 2) {
			double waitTime = limiter.acquire(i);
			System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime);
		}
	}
}