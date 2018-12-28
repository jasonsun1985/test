package com.tec.readwritelock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        final Lock readLock = lock.readLock();
        final Lock writeLock = lock.writeLock();
        final Resource resource = new Resource();
        final Random random = new Random();
        for(int i=0;i<20;++i){//写线程
            new Thread(){
                public void run() {
                   writeLock.lock();
                   try {
                       resource.setValue(resource.getValue()+1);
                       System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+" - "+Thread.currentThread()+"获取了写锁，修正数据为："+resource.getValue());
                       Thread.sleep(random.nextInt(1000));//随机休眠
                   } catch (Exception e) {
                       e.printStackTrace();
                   }finally{
                       writeLock.unlock();
                   }
                };
            }.start();
         }
         for(int i=0;i<20;++i){//读线程
            new Thread(){
                public void run() {
                   readLock.lock();
                   try {
                       System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+" - "+Thread.currentThread()+"获取了读锁，读取的数据为："+resource.getValue());
                       Thread.sleep(random.nextInt(1000));//随机休眠
                   } catch (Exception e) {
                       e.printStackTrace();
                   } finally{
                       readLock.unlock();
                   }
                };
            }.start();
         }
    }
    
}
