/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：AcceptorIdleStateTrigger.java
 *  版本变更记录（可选）：修改日期2017年11月13日  下午5:53:06，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.other;

import io.netty.channel.ChannelHandler;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
import io.netty.handler.timeout.IdleState;  
import io.netty.handler.timeout.IdleStateEvent;  
  
  
@ChannelHandler.Sharable  
public class AcceptorIdleStateTrigger extends ChannelInboundHandlerAdapter {  
  
    @Override  
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {  
        if (evt instanceof IdleStateEvent) {  
            IdleState state = ((IdleStateEvent) evt).state();  
            if (state == IdleState.READER_IDLE) {  
                throw new Exception("idle exception");  
            }  
        } else {  
            super.userEventTriggered(ctx, evt);  
        }  
    }  
}  
