package com.collect.dataformat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2017年4月14日<br>
 * <p>
 * </p>
 * <br>
 * @author R07400<br>
 * @version testTec v6.2.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 * DateFormats 是非线程安全的
 * Date formats are not synchronized.
* It is recommended to create separate format instances for each thread. --建议为每个线程创建单独的格式实例
* If multiple threads access a format concurrently, it must be synchronized --如果多个线程同时访问一个格式，则必须同步
 * 
 * 
 */
public class UnSafeThreadDateFormat {
    
	public static void main(String[] args) {
        Date today = new Date();
        Date tomorrow = new Date(today.getTime()+1000*60*60*24);
        System.out.println(today); // 今天是2018-05-23
        System.out.println(tomorrow); // 明天是2018-05-24
        Thread thread1 = new Thread(new Thread1(today));
        thread1.start();
        Thread thread2 = new Thread(new Thread2(tomorrow));
        thread2.start();
    }
    
}
class Thread1 implements Runnable{
    private Date date;
    public Thread1(Date date){
        this.date = date;
    }
    public void run() {
        for(;;){// 一直循环到出问题为止吧。
//            String strDate = UnSafeDataUtil.format(date);
            String strDate = SafeDataUtil.format(date);
            if(!"2018-05-23".equals(strDate)){
                System.err.println("错误的今日日期　：　"+strDate);
                System.exit(0);
//                break;
            }
        }
    }
}
class Thread2 implements Runnable{
    private Date date;
    public Thread2(Date date){
        this.date = date;
    }
    public void run() {
        for(;;){
//            String strDate = UnSafeDataUtil.format(date);
            String strDate = SafeDataUtil.format(date);
            if(!"2018-05-24".equals(strDate)){
                System.err.println("错误的明日日期 ：　"+strDate);
                System.exit(0);
//                break;
            }
        }
    }
}
