package com.collect.any;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TaskMission extends TimerTask {
	public static void main(String[] args) {
		TaskMission timeMission = new TaskMission();
		Timer timer = new Timer();
		timer.schedule(timeMission,0,2000);
	}
	public void run() {
		System.out.println(new Date());
	}

}
