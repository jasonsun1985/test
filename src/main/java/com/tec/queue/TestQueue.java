/**
 *  软件版权：SUNLEI
 *  系统名称：serverBoot
 *  文件名称：TestQueue.java
 *  版本变更记录（可选）：修改日期2017年12月13日  上午11:45:52，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/** 
 * @Description:
 * <p>创建日期：2017年12月13日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TestQueue {
	private static LinkedBlockingQueue<Integer> concurrentLinkedQueue = new LinkedBlockingQueue<Integer>(10);
	private static ArrayBlockingQueue<Integer> concurrentArrayQueue = new ArrayBlockingQueue<Integer>(10);
	private static ExecutorService executorService = Executors.newFixedThreadPool(20);
	static class P implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 100000; i++) {
				try {
					concurrentLinkedQueue.put(i);
//					Thread.sleep(300);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	static class C implements Runnable {

		@Override
		public void run() {
			try {
				while(true) {
					System.out.println(concurrentLinkedQueue.take());
					Thread.sleep(200);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		executorService.execute(new P());
		for (int i = 0; i < 10; i++) {
			executorService.execute(new C());
		}
	}
	
}
