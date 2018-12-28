package com.collect.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCountDownLatch {
	public static void main(String[] args) throws InterruptedException {
	   long start = System.currentTimeMillis();
	   // 开始的倒数锁
	   final CountDownLatch begin = new CountDownLatch(1);
	   // 结束的倒数锁
	   final CountDownLatch end = new CountDownLatch(10000);
	   // 10线程
	   final ExecutorService exec = Executors.newFixedThreadPool(10);
	   for (int index = 0; index < 10000; index++) {
	    final int NO = index + 1;
	    Runnable run = new Runnable() {
	     public void run() {
	      try {
	       begin.await();
	       Thread.sleep((long) (Math.random() * 10));
	      } catch (InterruptedException e) {
	      } finally {
	    	end.countDown();
	      }
	     }
	    };
	    exec.submit(run);//分配线程
	   }
	   System.out.println("Game Start");
	   begin.countDown();
	   end.await();//解除阻塞
	   System.out.println("Game Over");
	   exec.shutdown();
	   System.out.print("共计用时 ");
	   System.out.println(System.currentTimeMillis() - start);
	}
	}