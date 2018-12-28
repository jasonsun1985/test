package com.collect.any;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageTransfer {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://c.hiphotos.baidu.com/image/pic/item/4d086e061d950a7be822550e03d162d9f3d3c9e1.jpg");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		// 通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		// 得到图片的二进制数据，以二进制封装得到数据
		byte[] data = readInputStream(inStream);
		File imageFile = new File("C:\\Users\\R07400\\Desktop\\Image.jpg");
		// 创建输出流
		FileOutputStream outStream = new FileOutputStream(imageFile);
		// 写入数据
		outStream.write(data);
		// 关闭输出流
		outStream.close();
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while((len = inStream.read(buffer))!=-1){
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}
}