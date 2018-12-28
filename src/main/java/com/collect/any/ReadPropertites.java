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

public class ReadPropertites {
	
	private static ReadPropertites readPropertites = null;
	
	private ReadPropertites(){
	}
	public static synchronized  ReadPropertites getInstance(){
		 if(readPropertites==null){
			 readPropertites = new ReadPropertites();
         }
        return readPropertites;
	}
	public void reLoadProperties(File file) {
		Properties pro = new Properties();
		Map<String, String> mapInfo = new HashMap<String, String>();
			try {
				pro.load(new FileInputStream(new File(file.getAbsolutePath())));
				//实现自动加载属性功能
				Set<Entry<Object, Object>> set = pro.entrySet();
				for(Entry entry : set ) {
					System.out.println("key: "+entry.getKey().toString()+"  value: "+entry.getValue().toString());
					mapInfo.put(entry.getKey().toString(), entry.getValue().toString());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
