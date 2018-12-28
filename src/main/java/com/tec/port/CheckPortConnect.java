package com.tec.port;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CheckPortConnect {
    public static boolean isConnectable(String host, int port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public static void main(String[] args) {
        boolean isConn = isConnectable("localhost", 18500);
        System.out.println(isConn);
        if (!isConn) {
            //TODO 启动MINA Server服务
        }
    }
}
