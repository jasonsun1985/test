package com.tec.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 以下是一个带方法的类，它设置了 ScheduledExecutorService ，2秒后，在 1 分钟内每 10 秒钟蜂鸣一次
 * 
 * @author yaokj
 * 
 */
class BeeperControl {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public void beepForAnHour() {
		final Runnable beeper = new Runnable() {
		public void run() {
				System.out.println("beep");
			}
		};
		//12秒蜂鸣一次
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 2, 10, TimeUnit.SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
				scheduler.shutdown();
			}
		}, 60, TimeUnit.SECONDS);
	}
}

public class ScheduledExecutorServiceDemo {

	public static void main(String[] args) {

		new BeeperControl().beepForAnHour();
	}

}
