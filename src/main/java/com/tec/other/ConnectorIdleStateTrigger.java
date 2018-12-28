/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：ConnectorIdleStateTrigger.java
 *  版本变更记录（可选）：修改日期2017年11月13日  下午5:54:47，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.other;

import io.netty.buffer.ByteBuf;  
import io.netty.buffer.Unpooled;  
import io.netty.channel.ChannelHandler.Sharable;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
import io.netty.handler.timeout.IdleState;  
import io.netty.handler.timeout.IdleStateEvent;  
import io.netty.util.CharsetUtil;  
  
@Sharable  
public class ConnectorIdleStateTrigger extends ChannelInboundHandlerAdapter {  
      
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",  
            CharsetUtil.UTF_8));  
  
    @Override  
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {  
        if (evt instanceof IdleStateEvent) {  
            IdleState state = ((IdleStateEvent) evt).state();  
            if (state == IdleState.WRITER_IDLE) {  
                // write heartbeat to server  
                ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());  
            }  
        } else {  
            super.userEventTriggered(ctx, evt);  
        }  
    }  
}  
