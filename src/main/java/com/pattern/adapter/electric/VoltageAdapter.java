/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：VoltageAdapter.java
 *  版本变更记录（可选）：修改日期2018年2月12日  下午5:05:49，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.pattern.adapter.electric;

/** 
 * @Description:
 * <p>创建日期：2018年2月12日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class VoltageAdapter implements Voltage5 {
	private Voltage220 voltage220;
	public VoltageAdapter(Voltage220 voltage220){
		this.voltage220 = voltage220;
	}
	@Override
	public int output5V() {
		int src = voltage220.output220V();
        System.out.println("适配器工作开始适配电压");
        int dst = src / 44;
        System.out.println("适配完成后输出电压：" + dst);
        return dst;
	}
	public static void main(String[] args) {
		System.out.println("\n===============对象适配器==============");
        VoltageAdapter voltageAdapter = new VoltageAdapter(new Voltage220());
        Mobile mobile = new Mobile();
        mobile.charging(voltageAdapter);
	}

}
