/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：Voltage220.java
 *  版本变更记录（可选）：修改日期2018年2月12日  下午5:02:21，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.pattern.adapter.electric;

/** 
 * @Description:
 * <p>创建日期：2018年2月12日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class Voltage220 {
	public int output220V() {
		int src = 220;
		System.out.println("我是" + src + "V");
		return src;
	}
}
