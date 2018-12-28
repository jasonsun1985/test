package com.collect.filelisener;

import java.io.File;


public class FileListenerImpl implements FileListener {

	public void fileChanged(File file) {
		System.out.println(" File [ " + file.getName() + " ] changed At: "
				+ new java.util.Date());
	}
}
