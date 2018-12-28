/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：Test.java
 *  版本变更记录（可选）：修改日期2018年5月6日  下午7:49:51，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.rtsp;

import java.net.InetSocketAddress;

public class Test {
	public static final String Key_remoteAddress = "127.0.0.1";
	public static final String Key_localAddress = "127.0.0.1";
	public static final String Key_rtspUrl = "rtsp://:8554/camera";

	public static void main(String[] args) {
		try {
			RTSPClient client = new RTSPClient(new InetSocketAddress(Key_remoteAddress, 8554),
					new InetSocketAddress(Key_localAddress, 0), Key_rtspUrl);
			client.initConfig(null, null, false);
			client.start();
		}
		catch (Exception e) {
			System.out.print("测试发生异常：" + e.getMessage());
			e.printStackTrace();
		}
	}
}