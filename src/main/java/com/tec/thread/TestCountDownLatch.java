package com.tec.thread;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    private static final CountDownLatch latch = new CountDownLatch(2);
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("第一个子线程运行开始:" + System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第一个子线程运行结束:" + System.currentTimeMillis());
                latch.countDown();
               
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("第二个子线程运行开始:" + System.currentTimeMillis());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第二个子线程运行结束:" + System.currentTimeMillis());
                latch.countDown();
            }
        }).start();
        try {
            latch.await();
            System.out.println("子线程运行结束,主线程开始" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
