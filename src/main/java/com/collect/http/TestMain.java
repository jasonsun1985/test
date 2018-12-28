package com.collect.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;  
import java.util.Map;  
//对接口进行测试  
//调用post请求的测试代码
public class TestMain {
	// private String url = "https://192.168.1.101/";
	private String url = "https://www.baidu.com/";
	private String charset = "utf-8";
	private HttpClientUtil httpClientUtil = null;

	public TestMain() {
		httpClientUtil = new HttpClientUtil();
	}

	public void test() {
		String httpOrgCreateTest = url + "httpOrg/create";
		Map<String, String> createMap = new HashMap<String, String>();
		createMap.put("authuser", "*****");
		createMap.put("authpass", "*****");
		createMap.put("orgkey", "****");
		createMap.put("orgname", "****");
		String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest, createMap, charset);
		System.out.println("result:" + httpOrgCreateTestRtn);
	}

	public static void main(String[] args) {
		TestMain main = new TestMain();
		main.test();
		
//		FileInputStream fin;
//		byte[] bytes = null;
//		try {
//			fin = new FileInputStream(new File("D:\\temp\\1.jpg"));
//			bytes  = new byte[fin.available()];  
//			fin.read(bytes); 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//style=0表示不分页1表示分页&currentpage=当前页&pageline=每页的显示数目
		HttpClientUtil.getHttpResult("http://172.17.161.34:8888/db.cgi?cmd=getdevice");
//		System.out.println(HttpClientUtil.getHttpResult("http://172.17.161.34:8888/db.cgi?cmd=getdevice&style=0"));
	}
}
