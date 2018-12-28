/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：TestSchedulerThreadPool.java
 *  版本变更记录（可选）：修改日期2017年11月24日  上午11:07:57，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/** 
 * @Description:
 * <p>创建日期：2017年11月24日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TestSchedulerThreadPool {
	public static void main(String[] args) {
		int count = 0;
		/**
		 * scheduleAtFixedRate()：安排所提交的Runnable任务按指定的间隔重复执行。
		 */
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		final ScheduledFuture future = scheduler.scheduleAtFixedRate(new TestSchedulerThreadPool().new Dispatcher(count),0, 2, TimeUnit.SECONDS);
		/**
		 *schedule(task, initDelay ): 安排所提交的Callable或Runnable任务在initDelay指定的时间后执行。注意不是重复！！
		 * 
		 */
//		Executors.newSingleThreadScheduledExecutor().schedule(new TestSchedulerThreadPool().new Dispatcher(), 0, TimeUnit.SECONDS);
		
		Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
			@Override
			public void run() {
				future.cancel(false);
			}
		}, 20, TimeUnit.SECONDS);
	}
	private class Dispatcher implements Runnable {
		public int count;
		public Dispatcher(int count) {
			this.count = count;
		}

		@Override
		public void run() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				if(count == 5){
					Thread.sleep(6000);
				}
				System.out.println("现在时间： " + sdf.format(new Date()));
				count++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
	}
}
