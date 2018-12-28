package com.pattern.decorator;

public class TestPeople {

    public static void main(String[] args) {
        People SpecificMan = new SpecificMan();
        People superMan = new SuperMan(SpecificMan);
        People flashMan = new FlashMan(SpecificMan);
        superMan.move();
        flashMan.move();

    }

}
