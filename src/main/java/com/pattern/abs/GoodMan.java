package com.pattern.abs;

public class GoodMan implements People {

    public GoodMan() {
    }

    @Override
    public void doSomething() {
        System.out.println("Do Good things!");
    }
}