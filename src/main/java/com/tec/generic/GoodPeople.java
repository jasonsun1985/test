package com.tec.generic;

public class GoodPeople implements People<GoodPeople> {
    @Override
    public GoodPeople doSomething(GoodPeople t) {
        System.out.println("好人做好事");
        return new GoodPeople();
    }

}
