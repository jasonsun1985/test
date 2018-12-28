package com.tec.cas;

public class TestSynchronized {
	  public  int inc = 0;
	    public synchronized void increase() {
	        inc++;
	    }
	    
	    public static void main(String[] args) {
	        final TestSynchronized test = new TestSynchronized();
	        for(int i=0;i<10;i++){
	            new Thread(){
	                public void run() {
	                    for(int j=0;j<1000;j++)
	                        test.increase();
	                };
	            }.start();
	        }
	        System.out.println("Thread.activeCount() >>>>>>> "+Thread.activeCount());
	        while(Thread.activeCount()>1)  //保证前面的线程都执行完
//	            Thread.yield();//注释掉结果不同
	        Runtime.getRuntime().addShutdownHook(new ThreadTest());
	        System.out.println(test.inc);
	    }
	   
}
 class ThreadTest extends Thread {
	@Override
	public void run() {
		System.out.println("addShutdownHook");
		super.run();
	}
   }

