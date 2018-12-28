package com.pattern.decorator;

public class Evolution implements People {
    private People people;
    /**
     * @param people
     */
    public Evolution(People people) {
        super();
        this.people = people;
    }

    @Override
    public void move() {
        people.move();
        // TODO Auto-generated method stub

    }

}
