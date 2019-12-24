package com.tec.akka2;

import java.net.InetSocketAddress;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.io.Tcp;
import akka.io.Tcp.Bound;
import akka.io.Tcp.CommandFailed;
import akka.io.Tcp.Connected;
import akka.io.TcpMessage;

public class Server extends AbstractActor {

	final ActorRef manager;

	public Server(ActorRef manager) {
		this.manager = manager;
	}

	public static Props props(ActorRef manager) {
		return Props.create(Server.class, manager);
	}

	@Override
	public void preStart() throws Exception {
		final ActorRef tcp = Tcp.get(getContext().getSystem()).manager();
		// 老样子，是用来通知进行创建服务端actor
		tcp.tell(TcpMessage.bind(getSelf(), new InetSocketAddress("localhost", 3307), 100), getSelf());
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Bound.class, msg -> {
			manager.tell(msg, getSelf());
		}).match(CommandFailed.class, msg -> {
			getContext().stop(getSelf());

		}).match(Connected.class, conn -> {
			// 有客户端连接
			manager.tell(conn, getSelf());
			// 这个handler为自己实现的actor，即可指定让某个actor来处理接收的数据;
			final ActorRef handler = getContext().actorOf(Props.create(SimplisticHandler.class));

			// 注册一个handler来进行接收客户端传来的数据.
			getSender().tell(TcpMessage.register(handler), getSelf());
		}).build();
	}

}
