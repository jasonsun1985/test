package com.tec.akkanew;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/** 
 * @Description:
 * <p>创建日期：2019年4月7日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TestActor2 {
	public static void main(String[] args) throws java.io.IOException {
	    ActorSystem system = ActorSystem.create("learn-akka");
	    ActorRef firstRef = system.actorOf(Props.create(TestActor.class), "A-actor");
	    System.out.println("第一个: " + firstRef);
	    firstRef.tell("typeMsg1", ActorRef.noSender());
	    System.out.println(">>> Press ENTER to exit <<<");
	    try {
	      System.in.read();
	    } finally {
	      system.terminate();
	    }
	  }
}
