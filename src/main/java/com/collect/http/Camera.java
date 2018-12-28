/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：Camera.java
 *  版本变更记录（可选）：修改日期2017年12月6日  下午4:49:02，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.http;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/** 
 * @Description:
 * 样式：
 * <camera>
  <page>
    <style>0</style>
    <totalrecord>1</totalrecord>
  </page>
  <datas>
    <dev>
      <name>测试</name>
      <serial/>
      <ip>192.168.1.166</ip>
      <port>8899</port>
      <user>admin</user>
      <pass/>
      <factroytype>7</factroytype>
      <hardtype>0</hardtype>
      <longitude/>
      <latitude/>
    </dev>
  </datas> 
</camera>
 * <p>创建日期：2017年12月6日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
@XStreamAlias("camera")
public class Camera {
	@XStreamAlias("page")
	public Page page;
	@XStreamAlias("datas")
	public Datas datas;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Datas getDatas() {
		return datas;
	}

	public void setDatas(Datas datas) {
		this.datas = datas;
	}

	public static class Datas {
		@XStreamImplicit(itemFieldName = "dev")
		public List<Dev> devList;

		public List<Dev> getDevList() {
			return devList;
		}

		public void setDevList(List<Dev> devList) {
			this.devList = devList;
		}
	}

	public static class Dev {
		@XStreamAlias("port")
		public String port;
		@XStreamAlias("serial")
		public String serial;
		@XStreamAlias("ip")
		public String ip;
		@XStreamAlias("user")
		public String user;
		@XStreamAlias("pass")
		public String pass;
		@XStreamAlias("factroytype")
		public String factroytype;
		@XStreamAlias("hardtype")
		public String hardtype;
		@XStreamAlias("netstate")
		public String netstate;
		@XStreamAlias("name")
		public String name;
		@XStreamAlias("longitude")
		public String longitude;
		@XStreamAlias("latitude")
		public String latitude;

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getSerial() {
			return serial;
		}

		public void setSerial(String serial) {
			this.serial = serial;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public String getFactroytype() {
			return factroytype;
		}

		public void setFactroytype(String factroytype) {
			this.factroytype = factroytype;
		}

		public String getHardtype() {
			return hardtype;
		}

		public void setHardtype(String hardtype) {
			this.hardtype = hardtype;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNetstate() {
			return netstate;
		}

		public void setNetstate(String netstate) {
			this.netstate = netstate;
		}

		@Override
		public String toString() {
			return "Dev [port=" + port + ", serial=" + serial + ", ip=" + ip + ", user=" + user + ", pass=" + pass
					+ ", factroytype=" + factroytype + ", hardtype=" + hardtype + ", netstate=" + netstate + ", name="
					+ name + ", longitude=" + longitude + ", latitude=" + latitude + "]";
		}
	}

	public static class Page {
		@XStreamAlias("style")
		public String style;
		@XStreamAlias("totalrecord")
		public String totalrecord;

		public String getStyle() {
			return style;
		}

		public void setStyle(String style) {
			this.style = style;
		}

		public String getTotalrecord() {
			return totalrecord;
		}

		public void setTotalrecord(String totalrecord) {
			this.totalrecord = totalrecord;
		}
	}
}
