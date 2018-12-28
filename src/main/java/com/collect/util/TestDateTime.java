package com.collect.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TestDateTime {
	public static void main(String[] args) {
		Date date = new Date(System.currentTimeMillis());
//		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");  
//		TimeZone.setDefault(tz);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));
	}
}
