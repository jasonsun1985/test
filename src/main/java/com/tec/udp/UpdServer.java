/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：UpdServer.java
 *  版本变更记录（可选）：修改日期2017年8月14日  下午6:17:03，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
/** 
 * @Description:
 * <p>创建日期：2017年8月14日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */

public class UpdServer {
    private byte[] buff = new byte[2048];
    private DatagramPacket dp;
    private DatagramSocket ds;
    private InetSocketAddress socketAddress;
    public UpdServer(String host, int port){
        socketAddress = new InetSocketAddress(host, port);
        try {
            ds = new DatagramSocket(socketAddress);
        } catch (SocketException e) {
            System.out.println("连接失败:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        System.out.println("服务器开启监听...");
    }
    
    private String receive(String host, int port) {
        String info = "";
        try{
            dp = new DatagramPacket(buff, 0, buff.length);
            ds.receive(dp);
            info = new String(dp.getData(), 0, dp.getLength());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
    
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8888;
        UpdServer server = new UpdServer(host, port);
        String info = server.receive(host, port);
        System.out.println(info);
        //发送信息到客户端
        server.send(new String("TEST "));
    }

    private void send(String string) {
        DatagramPacket dps = new DatagramPacket(buff, 0, buff.length, dp.getAddress(), dp.getPort());
        dps.setData(string.getBytes());
        try {
            ds.send(dps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
