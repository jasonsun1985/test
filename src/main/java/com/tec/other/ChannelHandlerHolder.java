/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：ChannelHandlerHolder.java
 *  版本变更记录（可选）：修改日期2017年11月13日  下午5:44:55，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.other;

import io.netty.channel.ChannelHandler;

/** 
 * @Description: 
 * 客户端的ChannelHandler集合，由子类实现，这样做的好处： 
 * 继承这个接口的所有子类可以很方便地获取ChannelPipeline中的Handlers 
 * 获取到handlers之后方便ChannelPipeline中的handler的初始化和在重连的时候也能很方便 
 * 地获取所有的handlers
 * <p>创建日期：2017年11月13日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public interface ChannelHandlerHolder {
	ChannelHandler[] handlers();  
}
