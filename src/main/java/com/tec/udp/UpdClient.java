/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：UpdClient.java
 *  版本变更记录（可选）：修改日期2017年8月14日  下午6:17:47，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import net.sf.json.JSONObject;
/** 
 * @Description:
 * <p>创建日期：2017年8月14日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */

public class UpdClient {
    private byte[] buff = new byte[2048];
    private DatagramPacket dp;
    private DatagramSocket ds;
    public UpdClient(){
        try {
            ds = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        UpdClient updClient = new UpdClient();
        String host = "127.0.0.1";
        int port = 8888;
        updClient.send(host, port, getJSON().getBytes());
        
        //接受
        String info = updClient.receive();
        System.out.println(info);
    }

    /**
	 * @Description:
	 * 创建人：SUNLEI, 2017年8月16日 上午11:53:03
	 * 修改人：SUNLEI, 2017年8月16日 上午11:53:03
	 * @return 
	 * @return String  
	 * @throws
	 */
	private static String getJSON() {
		JSONObject obj = new JSONObject();
		obj.put("name", "jason");
		obj.put("age", "32");
		obj.put("company", "RUIJIE");
		obj.put("location", "TJ-huigu");
		return obj.toString();
	}

	private String receive() {
        DatagramPacket dpr = new DatagramPacket(buff, buff.length);
        String info = "";
        try {
            ds.receive(dpr);
            info = new String(dpr.getData(), 0, dpr.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }

    private void send(String host, int port, byte[] b) {
        try{
            dp = new DatagramPacket(buff, 0, buff.length, InetAddress.getByName(host), port);
            dp.setData(b);
            ds.send(dp);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}