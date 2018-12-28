package com.pattern.abs;

public class SocialServices {
    private People people;
    public SocialServices(final People people) {
        this.people = people;
    }
    private void getHumanBeings(){
        people.doSomething();
    }
    public static void main(String[] args){
        GoodMan goodMan = new GoodMan();
        SocialServices s = new SocialServices(goodMan);
        s.getHumanBeings();
    }

}
