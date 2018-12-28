package com.pattern.decorator;

public class SuperMan extends Evolution {

    public SuperMan(People people) {
        super(people);
    }
    @Override
    public void move() {
        System.out.println("Superman flying");
    }
}
