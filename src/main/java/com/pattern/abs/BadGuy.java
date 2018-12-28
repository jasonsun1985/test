package com.pattern.abs;

public class BadGuy implements People  {

    public BadGuy() {
    }

    @Override
    public void doSomething() {
        System.out.println("do Bad things!");
    }
}
