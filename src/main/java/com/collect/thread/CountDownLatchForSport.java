package com.collect.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchForSport {
	public static void main(String[] args) {
		final int count = 8;
		System.out.println("运动员开始就位。");
		final CountDownLatch  c = new CountDownLatch(count);
		for (int i = 0; i < count; i++) {
			final int num = i;
			new Thread(new Runnable() {
				public void run() {
					System.out.println((num+1)+ " 号运动员做好准备 ");
					try {
						TimeUnit.SECONDS.sleep(new Random().nextInt(4)+2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println((num+1)+" 号运动员就位");
					c.countDown();
				}
			}).start();
		}
		System.out.println("等待所有运动员就位");
		try {
//			await(int timeout, TimeUnit unit)
			c.await();
			System.out.println("比赛开始 ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}