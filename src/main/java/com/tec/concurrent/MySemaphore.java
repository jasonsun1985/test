package com.tec.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Java 5.0里新加了4个协调线程间进程的同步装置，它们分别是：
 * Semaphore, CountDownLatch, CyclicBarrier和Exchanger.
 * 本例主要介绍Semaphore。
 * Semaphore是用来管理一个资源池的工具，可以看成是个通行证，
 * 线程要想从资源池拿到资源必须先拿到通行证，
 * 如果线程暂时拿不到通行证，线程就会被阻断进入等待状态。
 */
public class MySemaphore extends Thread {
 
    private int i;
    private Semaphore semaphore;
     
    public MySemaphore(int i,Semaphore semaphore){
        this.i = i;
        this.semaphore = semaphore;
    }
     
    public void run(){
        if(semaphore.availablePermits() > 0){
         System.out.println(""+i+"有空位 ： ");
        }else{
            System.out.println(""+i+"等待，没有空位 ");
        }
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(""+i+"获得空位");
        try {
            Thread.sleep((int)Math.random()*10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(""+i+"使用完毕");
        semaphore.release();
    }
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ;i<10 ; i++){
            service.execute(new MySemaphore(i,semaphore));
        }
        service.shutdown();
        semaphore.acquireUninterruptibly(2);
        System.out.println("使用完毕，需要清扫了"); 
        semaphore.release(2);
    }
 
}
