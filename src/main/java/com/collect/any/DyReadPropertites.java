package com.collect.any;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class DyReadPropertites  {
	public static void main(String[] args) {
		new Thread(new listenDyReadPropertites()).start();
	}
}

class listenDyReadPropertites implements Runnable {
	public void run() {
		String proPath = new File("").getAbsolutePath()+"\\test.properties";
		Properties pro = new Properties();
		Map<String, String> mapInfo = new HashMap<String, String>();
		while(true) {
			try {
				pro.load(new FileInputStream(new File(proPath)));
				//实现自动加载属性功能
				Set<Entry<Object, Object>> set = pro.entrySet();
				for(Entry entry : set ) {
					System.out.println("key: "+entry.getKey().toString());
					System.out.println("value: "+entry.getValue().toString());
					mapInfo.put(entry.getKey().toString(), entry.getValue().toString());
				}
				Thread.sleep(5000);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}