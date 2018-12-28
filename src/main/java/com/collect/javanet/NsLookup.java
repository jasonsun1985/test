package com.collect.javanet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NsLookup {
	 public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("www.baidu.com");
			System.out.println("www.baidu.com" + ": " + address.getHostAddress());
			System.out.println(InetAddress.getLocalHost().getHostName());
		}
		catch (UnknownHostException uhe)
		{
			System.err.println("Unable to find: " + args[0]);
		}
	}
}
