package com.tec.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class FutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService=Executors.newCachedThreadPool(); 
        Callable<Integer> c = new CallableTask();
        Callable<Integer> d = new CallableTask2();
        Future<Integer> future1 = executorService.submit(c);  
        Future<Integer> future2 = executorService.submit(d);  
        System.out.println("子线程1结果：" + future1.get());
        System.out.println("子线程2结果：" + future2.get());
        System.out.println(future1.get()+future2.get());
    }
}

class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程1在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++){
            sum += i;
        }
        return sum;
    }
}

class CallableTask2 implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		 System.out.println("子线程2在进行计算");
	        Thread.sleep(2000);
	        int sum = 0;
	        for(int i=0;i<200;i++){
	            sum += i;
	        }
	        return sum;
	}
	
}