package com.collect.thread;

public class KaixinTicektRight implements Runnable {
	private int ticketNum = 10;

	public void run() {
		for (int i = 0; i < 20; i++) {
			if (ticketNum>0) {
				System.out.println(Thread.currentThread().getName()+ "正在卖票"+this.ticketNum--);
			}
		}
	}
	public static void main(String[] args) {
		KaixinTicektRight k = new KaixinTicektRight();
		for (int i = 1; i < 5; i++) {
			Thread thread = new Thread(k, "售票点（"+i+"）");
			thread.start();
		}
	}
}
