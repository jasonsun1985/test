package com.tec.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class AkkaManager {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("car");
        ActorRef ref = actorSystem.actorOf(Props.create(CarShop.class));
        Benz b = new Benz();
        b.setPrice(1860000);
        BMW bmw = new BMW();
        bmw.setPrice(1780000);
        ref.tell(b, ref);
        ref.tell(bmw, ref);
        ref.tell(new String(), ref);
        System.out.println(ref.path());
        actorSystem.terminate();
    }
}
