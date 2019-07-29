package com.tec.akka2;

import akka.actor.AbstractActor;
import akka.actor.UntypedActor;

public class SimplisticHandler extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Throwable {
		System.out.println(message.toString());
	}


	
}
