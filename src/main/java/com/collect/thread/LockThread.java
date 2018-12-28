package com.collect.thread;

public class LockThread {
	public static void main(String[] args) {
		LockThread lockThread = new LockThread();
		Object obj = new Object();
		lockThread.enter(obj);
	}
	 public static void enter(Object obj) {  
	      System.out.println("Step 1");  
	      try {  
	    magic1(obj);  
	} catch (Exception e) {  
	    e.printStackTrace();  
	}  
	      System.out.println("Step 2");  
	      synchronized (obj) {  
	          System.out.println("Step 3 (never reached here)");    
	      }  
	  }  
	 static void magic1(final Object obj) throws Exception{  
		    final Thread t = new Thread(){  
		        public void run(){  
		            synchronized(this){  
		                synchronized(obj){  
		                    try {  
		                        notify();  
		                        join();  
		        } catch (InterruptedException e) {  
		        }  
		                }  
		            }  
		        }  
		    };  
		    synchronized(t){  
		        t.start();  
		        t.wait();  
		    }  
		}  
}
