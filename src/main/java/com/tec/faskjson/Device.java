/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：Device.java
 *  版本变更记录（可选）：修改日期2018年2月11日  上午11:41:43，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.faskjson;

/** 
 * @Description:
 * <p>创建日期：2018年2月11日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class Device {
	public String metricId;
	public String cameraId;
	public String color;
	public String metricName;
	public String value;
	public String getMetricId() {
		return metricId;
	}
	public void setMetricId(String metricId) {
		this.metricId = metricId;
	}
	public String getCameraId() {
		return cameraId;
	}
	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getMetricName() {
		return metricName;
	}
	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
