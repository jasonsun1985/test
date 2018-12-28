/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：HeartBeatServer.java
 *  版本变更记录（可选）：修改日期2017年11月13日  下午5:49:40，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.other;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

/** 
 * @Description:
 * <p>创建日期：2017年11月13日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class HeartBeatServer {  
    
    private final AcceptorIdleStateTrigger idleStateTrigger = new AcceptorIdleStateTrigger();  
      
    private int port;  
  
    public HeartBeatServer(int port) {  
        this.port = port;  
    }  
  
    public void start() {  
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);  
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
        try {  
            ServerBootstrap sbs = new ServerBootstrap().group(bossGroup, workerGroup)  
                    .channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO))  
                    .localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {  
                        protected void initChannel(SocketChannel ch) throws Exception {  
                            ch.pipeline().addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));  
                            ch.pipeline().addLast(idleStateTrigger);  
                            ch.pipeline().addLast("decoder", new StringDecoder());  
                            ch.pipeline().addLast("encoder", new StringEncoder());  
                            ch.pipeline().addLast(new HeartBeatServerHandler());  
                        };  
  
                    }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);  
            // 绑定端口，开始接收进来的连接  
            ChannelFuture future = sbs.bind(port).sync();  
  
            System.out.println("Server start listen at " + port);  
            future.channel().closeFuture().sync();  
        } catch (Exception e) {  
            bossGroup.shutdownGracefully();  
            workerGroup.shutdownGracefully();  
        }  
    }  
  
    public static void main(String[] args) throws Exception {  
        int port;  
        if (args.length > 0) {  
            port = Integer.parseInt(args[0]);  
        } else {  
            port = 8080;  
        }  
        new HeartBeatServer(port).start();  
    }  
  
}  
