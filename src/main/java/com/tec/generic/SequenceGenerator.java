package com.tec.generic;

import java.util.Random;

import org.junit.Test;

public class SequenceGenerator implements IGeneric<Integer> {
    @Override
    public Integer next() {
        Random rand = new Random();
        return rand.nextInt(100);
    }
    @Test
    public void execute() {
        SequenceGenerator generator = new SequenceGenerator();
        System.out.println(generator.next());
        System.out.println(generator.next());
        System.out.println(generator.next());
        System.out.println(generator.next());
    }
}
