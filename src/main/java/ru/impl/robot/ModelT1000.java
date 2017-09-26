package ru.impl.robot;

import ru.interfaces.Hand;
import ru.interfaces.Head;
import ru.interfaces.Leg;
import ru.interfaces.Robot;

public class ModelT1000 implements Robot{
    private Head head;
    private Hand hand;
    private Leg leg;

    private String color;
    private int year;
    private boolean soundEnable;

    public ModelT1000() {

    }

    public ModelT1000(Head head, Hand hand, Leg leg) {
        this.head = head;
        this.hand = hand;
        this.leg = leg;
    }

    public ModelT1000(String color, int year, boolean soundEnable) {
        this.color = color;
        this.year = year;
        this.soundEnable = soundEnable;
    }

    public ModelT1000(Head head, Hand hand, Leg leg, String color, int year, boolean soundEnable) {
        this.head = head;
        this.hand = hand;
        this.leg = leg;
        this.color = color;
        this.year = year;
        this.soundEnable = soundEnable;
    }

    public Head getHead() {

        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Leg getLeg() {
        return leg;
    }

    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isSoundEnable() {
        return soundEnable;
    }

    public void setSoundEnable(boolean soundEnable) {
        this.soundEnable = soundEnable;
    }

    public void action() {
        head.calc();
        hand.catchSomeThing();
        leg.go();
        System.out.println(color);
        System.out.println(year);
        System.out.println(soundEnable);
    }

    public void sleep() {
        System.out.println("T1000 sleep");
    }
}
