package com.tec.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class SemaphoreProductAndConsume {
	private final static int THREAD_POOLS = 10; 
	private final static BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
	private final static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOLS);
	public static void main(String[] args) {
		final Semaphore spConsume = new Semaphore(5);
		final Semaphore spProduct = new Semaphore(5);
		for (int i = 0; i < THREAD_POOLS; i++) {
			executorService.execute(new Consume(spConsume));
			executorService.execute(new Product(spProduct));
		}
		System.out.println("begin:" + new SimpleDateFormat("yyyy-MM-d HH:mm:ss").format(new Date()));
//		for (int i = 0; i < 30; i++) { 
////		while(true){
//			String input = new SimpleDateFormat("yyyy-MM-d HH:mm:ss").format(new Date());
//			try {
//				System.out.println("向队列里生产数据：" + input);
//				queue.put(input);
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

	public static class Consume implements Runnable {
		public Semaphore sp;
		public Consume(Semaphore sp) {
			this.sp = sp;
		}
		@Override
		public void run() {
			while (true) {
				try {
					// lock.lock();
					System.out.println("消费等待： " + Thread.currentThread().getName());
					sp.acquire();
					String input = queue.take();
					System.out.println(Thread.currentThread().getName() + " 从队列拿数据： " + input);
					Thread.sleep(500);
					// lock.unlock();
					sp.release();
					System.out.println(Thread.currentThread().getName() + " 已经释放消费信号量");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class Product implements Runnable {
		public Semaphore sp;
		public Product(Semaphore sp) {
			this.sp = sp;
		}

		@Override
		public void run() {
			String input = new SimpleDateFormat("yyyy-MM-d HH:mm:ss").format(new Date());
			while (true) {
				try {
					System.out.println("生产等待： " + Thread.currentThread().getName());
					sp.acquire();
					System.out.println(Thread.currentThread().getName() + " 向队列里生产数据：" + input);
					queue.put(input);
					System.out.println(Thread.currentThread().getName() + " ###########队列占用############：" + queue.size());
					Thread.sleep(1000);
					sp.release();
					System.out.println(Thread.currentThread().getName() + "已经释放生产信号量");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
