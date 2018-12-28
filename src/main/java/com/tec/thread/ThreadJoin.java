package com.tec.thread;

public class ThreadJoin {
	public static void main(String[] args) throws Exception {
		final Thread t1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("t1 run");
			}
		});
		final Thread t2 = new Thread(new Runnable() {
			public void run() {
//				try {
//					t1.join();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				System.out.println("t2 run");
			}
		});
		final Thread t3 = new Thread(new Runnable() {
			public void run() {
//				try {
//					t2.join();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				System.out.println("t3 run");
			}
		});
		Thread t4 = new Thread(new Runnable() {
			public void run() {
//				try {
//					t3.join();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				System.out.println("t4 run");
			}
		});
		t1.start();
		t1.join();
		t2.start();
		t2.join();
		t3.start();
		t3.join();
		t4.start();
	}
}
