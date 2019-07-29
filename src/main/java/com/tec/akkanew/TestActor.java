package com.tec.akkanew;


import java.util.HashMap;
import java.util.Map;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/** 
 * @Description:
 * <p>创建日期：2019年4月7日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TestActor extends AbstractActor {

	protected final LoggingAdapter log = Logging.getLogger(context().system(), this);
	protected final Map<String,Object> map = new HashMap<String,Object>();
	
	@Override
	public Receive createReceive() {
		//符合类型的消息才会执行
		return receiveBuilder().matchEquals("typeMsg1", p->{
			ActorRef secondRef = getContext().actorOf(Props.empty(), "B-actor");
			System.out.println("第二个: " + secondRef);
		}).build();
	}
}
