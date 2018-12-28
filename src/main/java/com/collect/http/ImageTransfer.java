/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：TestImage.java
 *  版本变更记录（可选）：修改日期2017年12月4日  上午11:31:24，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.http;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
  
public class ImageTransfer {
	public static void main(String[] args) throws Exception {
		// 先模拟一个图形byte[]
		byte[] b1 = image2Bytes("D:\\temp\\1.jpg");
		String path = "C:\\Users\\R07400\\Desktop\\test.jpg";
		// 存为文件
		bufferToImage(b1, path);
		System.out.println("转换完成");
	}

	public static void bufferToImage(byte[] b, String tagSrc) throws Exception {
		FileOutputStream fout = new FileOutputStream(tagSrc);
		// 将字节写入文件
		fout.write(b);
		fout.close();
	}

	private static byte[] image2Bytes(String imgSrc) throws Exception {
		FileInputStream fin = new FileInputStream(new File(imgSrc));
		// 可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
		byte[] bytes = new byte[fin.available()];
		// 将文件内容写入字节数组，提供测试的case
		fin.read(bytes);
		fin.close();
		return bytes;
	}

}