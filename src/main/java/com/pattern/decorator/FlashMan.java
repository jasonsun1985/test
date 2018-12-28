package com.pattern.decorator;

public class FlashMan extends Evolution {

    public FlashMan(People people) {
        super(people);
    }

    @Override
    public void move() {
        System.out.println("Running speed like lightning");
    }
    
}
