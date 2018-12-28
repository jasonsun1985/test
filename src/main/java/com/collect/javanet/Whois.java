package com.collect.javanet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;

public class Whois {
	public final static int DEFAULT_PORT = 43;
	public final static String DEFAULT_HOST = "whois.internic.net";// cn接口："whois.cnnic.net.cn"
																	// com接口："whois.internic.net"

	public static void main(String[] args) {
		InetAddress server;
		int port = DEFAULT_PORT;
		String str = "";
		try {
			server = InetAddress.getByName(DEFAULT_HOST);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		try {
			Socket theSocket = new Socket(server, port);
			Writer out = new OutputStreamWriter(theSocket.getOutputStream(),"UTF-8");
			out.write("dkd4pl.net \r\n");
			out.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(theSocket.getInputStream(), "UTF-8"));
			while ((str = br.readLine()) != null)
				System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}