package com.tec.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionProductAndConsume {
	private final static int THREAD_POOLS = 10;
	private final static int QUEUE_SIZE = 10;
	private final static BlockingQueue<String> queue = new LinkedBlockingQueue<String>(QUEUE_SIZE);
	private final static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOLS);
	private static Lock lock = new ReentrantLock();
	private static Condition consume = lock.newCondition();
	private static Condition product = lock.newCondition();

	public static void main(String[] args) {
		for (int i = 0; i < THREAD_POOLS; i++) {
			executorService.execute(new Consume());
			executorService.execute(new Product());
		}
		System.out.println("begin:" + new SimpleDateFormat("yyyy-MM-d HH:mm:ss").format(new Date()));
	}

	public static class Consume implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					lock.lock();
					System.out.println("Consume : " + queue.size());
					while (queue.isEmpty()) {
						consume.await();
					}
					System.out.println("消费： " + Thread.currentThread().getName());
					String input = queue.take();
					System.out.println(Thread.currentThread().getName() + " 从队列拿数据： " + input);
					// Thread.sleep(500);
					product.signal();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					lock.unlock();
				}
			}
		}
	}

	public static class Product implements Runnable {
		@Override
		public void run() {
			String input = new SimpleDateFormat("yyyy-MM-d HH:mm:ss").format(new Date());
			while (true) {
				try {
					lock.lock();
					System.out.println("Product : " + queue.size());
					while (queue.size() == QUEUE_SIZE) {
						product.await();
					}
					System.out.println("生产： " + Thread.currentThread().getName());
					System.out.println(Thread.currentThread().getName() + " 向队列里生产数据：" + input);
					queue.put(input);
					System.out.println(Thread.currentThread().getName() + " ###########队列占用############：" + queue.size());
					// Thread.sleep(1000);
					consume.signal();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					lock.lock();
				}
			}
		}
	}
}
