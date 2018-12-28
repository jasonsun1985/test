package com.collect.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerThreadPool {
    public static void main(String args[]){  
        //得到我们的池子  
    	ServerThreadPool tp = ServerThreadPool.getInstance();  
        //将我们的任务new出来  
        TestTask tt = new TestTask("sunlei");  
        //用池子执行任务  
        tp.execute(tt);  
    }  
    /** 
     * 任务池 
     *  
     * @author sunlei 
     */  
          
        // 单例，让池子只有一个  
        private ServerThreadPool() {  
        };  
      
        private static ServerThreadPool tp;  
      
        public static ServerThreadPool getInstance() {  
            if (tp == null) {  
                tp = new ServerThreadPool();  
            }  
            return tp;  
        }  
      
        // 构造一个任务池  
        // 参数说明：  
        // corePoolSize - 池中所保存的线程数，包括空闲线程。  
        // maximumPoolSize - 池中允许的最大线程数。  
        // keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。  
        // unit - keepAliveTime 参数的时间单位。  
        // workQueue - 执行前用于保持任务的队列。此队列仅保持由 execute 方法提交的 Runnable 任务。  
        // threadFactory - 执行程序创建新线程时使用的工厂。  
        // handler - 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序。  
        @SuppressWarnings("unchecked")  
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3,  
                TimeUnit.SECONDS, new ArrayBlockingQueue(3),  
                new ThreadPoolExecutor.DiscardOldestPolicy());  
      
        // 在池子里取一个线程执行任务  
        public void execute(Runnable task) {  
            threadPool.execute(task);  
        }
        /** 
         * 实现了Runnable接口的一个任务 
         * @author   sunlei 
         */  
        public static class TestTask implements Runnable {  
              
            //如果我们的任务需要一些数据，可以通过构造方法传进来  
            String param;  
            public TestTask(String param){  
                this.param = param;  
            }  
              
            public void run() {  
                // TODO Auto-generated method stub  
                //我们的任务  
                System.out.print("Hello"+param);  
            }  
          
        }
}
  
