package com.collect.thread;

public class KaixinTicektWrong extends Thread {
	private int ticketNum = 10;
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+ "正在卖票"+this.ticketNum--);
		}
	}
	public static void main(String[] args) {
		KaixinTicektWrong k1 = new KaixinTicektWrong();
		KaixinTicektWrong k2 = new KaixinTicektWrong();
		KaixinTicektWrong k3 = new KaixinTicektWrong();
		k1.start();
		k2.start();
		k3.start();
	}
}
