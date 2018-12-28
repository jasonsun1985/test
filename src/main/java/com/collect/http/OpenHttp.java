package com.collect.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class OpenHttp extends TimerTask {
	
//	private final String url = "https://www.baidu.com";
	private final String url = "http://172.17.161.34:8888/db.cgi?cmd=getdevice&style=1&currentpage=1&pageline=100&feature=1";
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new OpenHttp(),0, 30000);
	}

	private static void timingRequest(String url) {
		try {
			HttpURLConnection connection = openConnection(url);
			OutputStream out = connection.getOutputStream();
			out.flush();
			out.close();
			InputStream is = null;
			if(connection!=null) {
				is = connection.getInputStream();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while((line=br.readLine())!=null) {
				sb.append(line).append("\n");
			}
			System.out.println(sb.toString());
			if(connection!=null) {
				connection.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static HttpURLConnection openConnection(String url) {
		URL httpUrl = null;
		HttpURLConnection connection = null;
		try {
			httpUrl = new URL(url);
			connection = (HttpURLConnection)httpUrl.openConnection();
			connection.setConnectTimeout(1000);
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void run() {
		timingRequest(url);
	}
}
