package com.collect.filelisener;

import java.io.File;


public class Test {
	public static void main(String args[]) {

		FileMonitor monitor = new FileMonitor(1000);
		monitor.addFile(new File("D:\\temp\\test.txt"));
		monitor.addListener(new FileListenerImpl());
//		while (!false)
//		;
	}
}
