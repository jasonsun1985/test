package com.collect.broswer;

import java.io.IOException;

public class TestBroswer {
	public static void main(String[] args) {
		String url =  "www.baidu.com";
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
