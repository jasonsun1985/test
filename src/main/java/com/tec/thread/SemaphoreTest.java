package com.tec.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
    	//只能有三个线程同时访问
        Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        SemaphoreThread testA = new SemaphoreThread("A", semaphore);
//        SemaphoreThread testB = new SemaphoreThread("B", semaphore);
//        SemaphoreThread testC = new SemaphoreThread("C", semaphore);
//        SemaphoreThread testD = new SemaphoreThread("D", semaphore);
//        SemaphoreThread testE = new SemaphoreThread("E", semaphore);
//        SemaphoreThread testF = new SemaphoreThread("F", semaphore);
//        SemaphoreThread testG = new SemaphoreThread("G", semaphore);
//        testA.start();
//        testB.start();
//        testC.start();
//        testD.start();
//        testE.start();
//        testF.start();
//        testG.start();
        for (int i = 0; i < 10; i++) {
        	executorService.execute(new SemaphoreThread("第"+i+"个信号量 ", semaphore));
		}
    }

}
