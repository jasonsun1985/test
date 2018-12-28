/**
 *  软件版权：SUNLEI
 *  系统名称：serverBoot
 *  文件名称：IoBufferTOString.java
 *  版本变更记录（可选）：修改日期2017年12月7日  下午12:34:09，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.util;

import java.nio.ByteOrder;
import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;  
  
public class IoBufferTOString {

	public static String ioBufferToString1(IoBuffer iobuffer) {
		System.out.println("message = " + iobuffer + iobuffer.limit());
		iobuffer.flip(); // 调换buffer当前位置，并将当前位置设置成0
		byte[] b = new byte[iobuffer.limit()];
		iobuffer.get(b);
		// 此处用stringbuffer是因为
		// String类是字符串常量，是不可更改的常量。而StringBuffer是字符串变量，它的对象是可以扩充和修改的。
		StringBuffer stringBuffer = new StringBuffer();

		for (int i = 0; i < b.length; i++) {
			System.out.println("====" + b[i]);
			stringBuffer.append((char) b[i]); // 可以根据需要自己改变类型
			System.out.println(b[i] + "---------" + i);
		}
		return stringBuffer.toString();
	}

	public static void main(String[] args) throws CharacterCodingException {
		IoBuffer iobuffer = IoBuffer.allocate(8);
		iobuffer.order(ByteOrder.LITTLE_ENDIAN);
		// iobuffer.setAutoExpand(true);
		iobuffer.putChar('z');

		String str = ioBufferToString1(iobuffer);
		System.out.println(str);

	}

	public static String byteToString(byte[] b) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			stringBuffer.append((char) b[i]);
		}
		return stringBuffer.toString();
	}

	public static IoBuffer stringToIoBuffer(String str) {

		byte bt[] = str.getBytes();

		IoBuffer ioBuffer = IoBuffer.allocate(bt.length);
		ioBuffer.put(bt, 0, bt.length);
		ioBuffer.flip();
		return ioBuffer;
	}

	public static IoBuffer byteToIoBuffer(byte[] bt, int length) {

		IoBuffer ioBuffer = IoBuffer.allocate(length);
		ioBuffer.put(bt, 0, length);
		ioBuffer.flip();
		return ioBuffer;
	}

	public static byte[] ioBufferToByte(Object message) {
		if (!(message instanceof IoBuffer)) {
			return null;
		}
		IoBuffer ioBuffer = (IoBuffer) message;
		byte[] b = new byte[ioBuffer.limit()];
		ioBuffer.get(b);
		return b;
	}

	public static String ioBufferToString(Object message) {
		if (!(message instanceof IoBuffer)) {
			return "";
		}
		IoBuffer ioBuffer = (IoBuffer) message;
		byte[] b = new byte[ioBuffer.limit()];
		ioBuffer.get(b);
		StringBuffer stringBuffer = new StringBuffer();

		for (int i = 0; i < b.length; i++) {

			stringBuffer.append((char) b[i]);
		}
		return stringBuffer.toString();
	}

}     