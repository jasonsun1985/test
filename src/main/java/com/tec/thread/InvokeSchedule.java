package com.tec.thread;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class InvokeSchedule {
	private final Timer timer = new Timer();
	private static final InvokeSchedule i = new InvokeSchedule();
	private InvokeSchedule() {
	}
	public static InvokeSchedule getInstance(){
		return i;
	}
	public void startSchedule(){
		timer.schedule(new TimerTask() {
			int count = 1;
			@Override
			public void run() {
				if (count == 5) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						System.out.println("延迟5s");
						e.printStackTrace();
					}
				}
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				System.out.println("当前时间：" + sf.format(System.currentTimeMillis()) + "计划时间：" + sf.format(scheduledExecutionTime()));
				count++;
			}
		}, 0, 2000);
	}
	
	public static void main(String[] args) {
		InvokeSchedule.getInstance().startSchedule();
		
	}
	
}
