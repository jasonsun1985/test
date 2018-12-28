package com.tec.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreThread extends Thread {

    //创建有3个信号量的信号量计数器
    public Semaphore semaphore;

    public SemaphoreThread(String name, Semaphore semaphore) {
        setName(name);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println(getName() + " 取号等待... " + dateFormat(System.currentTimeMillis()));
            //取出一个信号
            semaphore.acquire();
            System.out.println(getName() + " 提供服务... " + dateFormat(System.currentTimeMillis()));
            sleep(1000);
            System.out.println(getName() + " 完成服务... " + dateFormat(System.currentTimeMillis()));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //释放一个信号
        semaphore.release();
        System.out.println(getName() + " 释放... " + dateFormat(System.currentTimeMillis()));
    }

	/**
	 * @Description:
	 * 创建人：SUNLEI, 2017年8月16日 下午3:23:22
	 * 修改人：SUNLEI, 2017年8月16日 下午3:23:22
	 * @param currentTimeMillis
	 * @return 
	 * @return String  
	 * @throws
	 */
	private String dateFormat(long currentTimeMillis) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		return sdf.format(new Date(currentTimeMillis));
	}
    

}
