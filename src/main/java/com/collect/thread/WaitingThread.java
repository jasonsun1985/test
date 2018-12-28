package com.collect.thread;

public class WaitingThread {
	public static void main(String[] args) throws InterruptedException {  
		  
        final Thread thread = new Thread(new Runnable() {
  
            public void run() {  
                try {  
                	System.out.println("此行代码使该线程进入timed_waiting状态");
                    Thread.sleep(10000);// 此行代码使该线程进入timed_waiting状态̬  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
        });  
  
        thread.start();  
  
        new Thread(new Runnable() {  
  
            public void run() {  
                try {  
                    thread.join();// 此行代码使该线程进入waiting状态，等待thread执行完毕
                    System.out.println("此行代码使该线程进入waiting状态，等待thread执行完毕");
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
  
        }).start();  
  
    }  
}
