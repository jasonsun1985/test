package com.tec.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewThreadTest {
    public static void main(String[] args) {
        ImcConnectThread i = new ImcConnectThread();
        i.start();
        System.out.println("线程继续");
    }
}
class ImcConnectThread extends Thread  {
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}