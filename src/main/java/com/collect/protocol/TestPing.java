/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：TestPing.java
 *  版本变更记录（可选）：修改日期2017年11月28日  上午8:51:30，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.protocol;

import java.net.InetAddress;

/** 
 * @Description:
 * <p>创建日期：2017年11月28日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TestPing {
	public static void main(String[] args) {
		for (int i = 0; i < 500; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(ping("172.17.160.182"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	public static boolean ping(String ipAddress) throws Exception {
        int  timeOut =  3000 ;  //超时应该在3钞以上
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);     // 当返回值是true时，说明host是可用的，false则不可。
        return status;
    }
}
