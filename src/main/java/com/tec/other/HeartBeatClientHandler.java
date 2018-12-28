/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：HeartBeatClientHandler.java
 *  版本变更记录（可选）：修改日期2017年11月13日  下午5:55:10，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.other;

import io.netty.channel.ChannelHandler.Sharable;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
import io.netty.util.ReferenceCountUtil;

import java.text.SimpleDateFormat;
import java.util.Date;  
  
@Sharable  
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("激活时间是：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		System.out.println("HeartBeatClientHandler channelActive");
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("停止时间是：" + new Date());
		System.out.println("HeartBeatClientHandler channelInactive");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = (String) msg;
		System.out.println(message);
		if (message.equals("Heartbeat")) {
			ctx.write("has read message from server");
			ctx.flush();
		}
		ReferenceCountUtil.release(msg);
	}
}  