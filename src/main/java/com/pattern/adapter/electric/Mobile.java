/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：Mobile.java
 *  版本变更记录（可选）：修改日期2018年2月12日  下午5:10:11，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.pattern.adapter.electric;

/** 
 * @Description:
 * <p>创建日期：2018年2月12日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class Mobile {
	public void charging(Voltage5 voltage5) {
        if (voltage5.output5V() == 5) {
            System.out.println("电压刚刚好5V，开始充电");
        } else if (voltage5.output5V() > 5) {
            System.out.println("电压超过5V，都闪开 我要变成note7了");
        }
    }
}
