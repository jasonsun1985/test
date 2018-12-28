package com.tec.cas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * 无锁队列
 */
public class RingBuffer {

    private AtomicInteger productIndex = new AtomicInteger(0);

    private AtomicInteger consumeIndex = new AtomicInteger(0);

    private int MAX_LENGTH = 4;

    private String[] dataArr = new String[MAX_LENGTH];

    private static final int maxSpinNums = 1000;

    private static final ExecutorService executorService = new ThreadPoolExecutor(
            3, 10, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(5000),
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r,
                        ThreadPoolExecutor executor) {
                    // discard
                    System.out.println("调用rejectedExecution");
                }
            });

    public boolean write(String content) {
        // 获取写入位置
        int oldWriteIndex = productIndex.get();

        // 追上一圈则写入失败
        if ((oldWriteIndex - consumeIndex.get()) >= MAX_LENGTH) {
            System.out.println("write: 追上一圈则写入失败");
            return false;
        }

        int indexAfterWrite = oldWriteIndex + 1;
        if (indexAfterWrite > Integer.MAX_VALUE) {
            indexAfterWrite = 0;
        }

        int spinNums = 0;
        for (;;) {
            if (spinNums++ >= maxSpinNums) {
                return false;
            }
        /*           
         *  CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，
         *  将内存值V修改为B，否则什么都不做。
         */             
            if (productIndex.compareAndSet(oldWriteIndex, indexAfterWrite)) {
                dataArr[oldWriteIndex & (MAX_LENGTH - 1)] = content;
                break;
            }
        }
        return true;
    }

    @Test
    public void test() {
        // 12转成二进制数是1100（前四位省略了），5转成二进制数是0101，则运算后的结果为0100即4
        System.out.println(12 & 5);
        System.out.println(12 | 5);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }

    public String read() {
        // 获取读取位置
        int oldReadIndex = consumeIndex.get();

        if (productIndex.get() <= oldReadIndex) {
            return null;
        }

        int indexAfterRead = oldReadIndex + 1;
        if (indexAfterRead > Integer.MAX_VALUE) {
            indexAfterRead = 0;
        }

        for (;;) {
            if (consumeIndex.compareAndSet(oldReadIndex, indexAfterRead)) {
                return dataArr[oldReadIndex & (MAX_LENGTH - 1)];
            }
        }
    }

    public static void main(String[] args) {
        final RingBuffer noLockBuffer = new RingBuffer();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(noLockBuffer.write("a"));
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(noLockBuffer.write("b"));
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(noLockBuffer.write("c"));
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(noLockBuffer.write("d"));
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(noLockBuffer.write("e"));
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(6000);
                        System.out.println("write result : "
                                + noLockBuffer.write(new SimpleDateFormat(
                                        "yyyy-MM-dd HH:mm:ss").format(new Date())));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        // consume
        while (true) {
            try {
                Thread.sleep(1000);

                String res = noLockBuffer.read();
                if (res != null) {
                    System.out.println("read result :" + res);
                }
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
