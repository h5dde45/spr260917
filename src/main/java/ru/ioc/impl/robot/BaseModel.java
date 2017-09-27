package ru.ioc.impl.robot;

import ru.ioc.interfaces.Hand;
import ru.ioc.interfaces.Head;
import ru.ioc.interfaces.Leg;
import ru.ioc.interfaces.Robot;

public abstract class BaseModel implements Robot {
    private Head head;
    private Hand hand;
    private Leg leg;

    public BaseModel() {
        System.out.println(this + " - BaseModel");
    }

    public BaseModel(Head head, Hand hand, Leg leg) {
        this.head = head;
        this.hand = hand;
        this.leg = leg;
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
}
