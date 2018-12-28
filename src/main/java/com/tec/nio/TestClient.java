package com.tec.nio;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 8888);
        InputStream inStram = s.getInputStream();
        OutputStream outStream = s.getOutputStream();
        // 输出
        PrintWriter out = new PrintWriter(outStream, true);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入：");
            String inline = scanner.nextLine();
            if (inline.equals("exit")){
                break;
            }
            System.out.println(">>>> " + inline);
            out.println(inline);
            out.flush();
            // 输入
            Scanner in = new Scanner(inStram);
            StringBuilder sb = new StringBuilder();
            String line = in.nextLine();
            sb.append(line);
            String response = sb.toString();
            System.out.println("response=" + response);
        }
    }
}
