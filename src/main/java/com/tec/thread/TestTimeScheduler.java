/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：TestTimeScheduler.java
 *  版本变更记录（可选）：修改日期2017年11月24日  上午11:36:26，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.thread;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

/** 
 * @Description:
 * <p>创建日期：2017年11月24日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TestTimeScheduler {
	public static void main(String[] args) {
		final Timer timer = new Timer();
		/*
			当前时间：2017-11-24 03:19:49计划时间：2017-11-24 03:19:49
			当前时间：2017-11-24 03:19:50计划时间：2017-11-24 03:19:50
			当前时间：2017-11-24 03:19:51计划时间：2017-11-24 03:19:51
			当前时间：2017-11-24 03:19:57计划时间：2017-11-24 03:19:52
			当前时间：2017-11-24 03:19:57计划时间：2017-11-24 03:19:53
			当前时间：2017-11-24 03:19:57计划时间：2017-11-24 03:19:54
			当前时间：2017-11-24 03:19:57计划时间：2017-11-24 03:19:55
			当前时间：2017-11-24 03:19:57计划时间：2017-11-24 03:19:56
			当前时间：2017-11-24 03:19:57计划时间：2017-11-24 03:19:57
			当前时间：2017-11-24 03:19:58计划时间：2017-11-24 03:19:58
			当前时间：2017-11-24 03:19:59计划时间：2017-11-24 03:19:59
		*/
		//任务耽搁了补点
//		 timer.scheduleAtFixedRate(new TimerTask() {
		
		/*
			当前时间：2017-11-24 03:21:47计划时间：2017-11-24 03:21:47
			当前时间：2017-11-24 03:21:48计划时间：2017-11-24 03:21:48
			当前时间：2017-11-24 03:21:49计划时间：2017-11-24 03:21:49
			当前时间：2017-11-24 03:21:55计划时间：2017-11-24 03:21:50
			当前时间：2017-11-24 03:21:55计划时间：2017-11-24 03:21:55
			当前时间：2017-11-24 03:21:56计划时间：2017-11-24 03:21:56
			当前时间：2017-11-24 03:21:57计划时间：2017-11-24 03:21:57
			当前时间：2017-11-24 03:21:58计划时间：2017-11-24 03:21:58
			当前时间：2017-11-24 03:21:59计划时间：2017-11-24 03:21:59
		*/
		//任务耽搁了就耽搁了，到点执行
		timer.schedule(new TimerTask() {// 分别注释这行和上面这行试一试效果
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
//				System.out.println("当前时间：" + sf.format(System.currentTimeMillis()));
				count++;
			}
		}, 0, 2000);
	}
}
