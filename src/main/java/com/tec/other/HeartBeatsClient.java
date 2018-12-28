/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：HeartBeatsClient.java
 *  版本变更记录（可选）：修改日期2017年11月13日  下午5:54:27，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.other;

import io.netty.bootstrap.Bootstrap;  
import io.netty.channel.Channel;  
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelHandler;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.EventLoopGroup;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.nio.NioSocketChannel;  
import io.netty.handler.codec.string.StringDecoder;  
import io.netty.handler.codec.string.StringEncoder;  
import io.netty.handler.logging.LogLevel;  
import io.netty.handler.logging.LoggingHandler;  
import io.netty.handler.timeout.IdleStateHandler;  
import io.netty.util.HashedWheelTimer;  
  
import java.util.concurrent.TimeUnit;  
  
public class HeartBeatsClient {

	protected final HashedWheelTimer timer = new HashedWheelTimer();

	private Bootstrap boot;

	private final ConnectorIdleStateTrigger idleStateTrigger = new ConnectorIdleStateTrigger();

	public void connect(int port, String host) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		boot = new Bootstrap();
		boot.group(group).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO));

		final ConnectionWatchdog watchdog = new ConnectionWatchdog(boot, timer, port, host, true) {

			public ChannelHandler[] handlers() {
				return new ChannelHandler[] { this, new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS), idleStateTrigger,
						new StringDecoder(), new StringEncoder(), new HeartBeatClientHandler() };
			}
		};
		ChannelFuture future;
		// 进行连接
		try {
			synchronized (boot) {
				boot.handler(new ChannelInitializer<Channel>() {

					// 初始化channel
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(watchdog.handlers());
					}
				});

				future = boot.connect(host, port);
			}

			// 以下代码在synchronized同步块外面是安全的
			future.sync();
		} catch (Throwable t) {
			throw new Exception("connects to  fails", t);
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int port = 80;
//		 int port = 161;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// 采用默认值
			}
		}
		new HeartBeatsClient().connect(port, "172.17.161.34");
	}

}