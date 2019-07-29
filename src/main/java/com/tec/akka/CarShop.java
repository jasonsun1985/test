package com.tec.akka;

import akka.actor.UntypedAbstractActor;

public class CarShop extends UntypedAbstractActor {

    @Override
    public void onReceive(Object obj) {
        if (obj instanceof BMW) {
            BMW bmw = (BMW)obj;
            System.out.println("BMW"+bmw.getPrice()); 
        } else if (obj instanceof Benz){
            Benz benz = (Benz)obj;
            System.out.println("Benz"+benz.getPrice()); 
        } else {
            System.out.println("Nothing");
        }
    }

}
