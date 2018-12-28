/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：HeartBeatServerHandler.java
 *  版本变更记录（可选）：修改日期2017年11月13日  下午5:53:28，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.other;

import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
  
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {  
  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        System.out.println("server channelRead..");  
        System.out.println(ctx.channel().remoteAddress() + "->Server :" + msg.toString());  
    }  
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        cause.printStackTrace();  
        ctx.close();  
    }  
  
}  
