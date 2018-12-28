package com.collect.thread;

public class Test extends Thread {
	public void run(){
		System.out.println("run thread ");
	}
	public static void main(String[] args) {
//		for (int i = 0; i < 690; i++) {
//			System.out.println(i);
//			new Thread(new Runnable() {
//				public void run() {
//					System.out.println(Thread.currentThread());
//				}
//			}).start();
//		}
		Test test = new Test();
		test.start();
	}
}
