/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：TestBuffer.java
 *  版本变更记录（可选）：修改日期2017年12月15日  下午5:21:26，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.test;

import java.nio.ByteBuffer;

/** 
 * @Description:
 * <p>创建日期：2017年12月15日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TestBuffer {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(8);
		buf.put((byte) 0x00);
		buf.put((byte) 0x01);
		buf.put((byte) 0x02);
		buf.put((byte) 0x03);
		System.out.println("连续写入4个：");
		System.out.println("\t" + buf);
		buf.flip();
		System.out.println("Flip后：");
		System.out.println("\t" + buf);
		byte b0 = buf.get();
		byte b1 = buf.get();
		System.out.println("连续读出2个：" + b0 + " " + b1);
		System.out.println("\t" + buf);
		buf.flip();
		System.out.println("Flip后：");
		System.out.println("\t" + buf);
		buf.compact();
		System.out.println("Compact后：（应用场景：上一次读没完，下一次写就开始了）");
		System.out.println("\t" + buf);
		buf.put((byte) 0x04);
		buf.put((byte) 0x05);
		System.out.println("再写入两个数：");
		System.out.println("\t" + buf);
		buf.flip();
		byte b2 = buf.get();
		byte b3 = buf.get();
		byte b4 = buf.get();
		byte b5 = buf.get();
		System.out.println("连续读出4个：" + b2 + " " + b3 + " " + b4 + " " + b5);
		System.out.println("\t" + buf);
	}
}
