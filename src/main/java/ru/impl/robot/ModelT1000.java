package ru.impl.robot;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import ru.interfaces.Hand;
import ru.interfaces.Head;
import ru.interfaces.Leg;

public class ModelT1000 extends BaseModel implements InitializingBean,DisposableBean{


    private String color;
    private int year;
    private boolean soundEnable;

    public ModelT1000() {
    }

    public ModelT1000(Head head, Hand hand, Leg leg) {
        super(head,hand,leg);
    }

    public ModelT1000(String color, int year, boolean soundEnable) {
        this.color = color;
        this.year = year;
        this.soundEnable = soundEnable;
    }

    public ModelT1000(Head head, Hand hand, Leg leg, String color, int year, boolean soundEnable) {
        super(head,hand,leg);
        this.color = color;
        this.year = year;
        this.soundEnable = soundEnable;
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
        getHead().calc();
        getHand().catchSomeThing();
        getLeg().go();
        System.out.println(color);
        System.out.println(year);
        System.out.println(soundEnable);
    }

    public void sleep() {
        System.out.println("T1000 sleep");
    }

    public void initObject() {
        System.out.println("init");
    }

    public void destroyObject() {
        System.out.println("destroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(this+" - destroy()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this+" - init()");
    }
}
