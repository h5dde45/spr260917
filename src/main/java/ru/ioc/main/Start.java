package ru.ioc.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ioc.impl.robot.ModelT1000;

public class Start {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("all_context.xml");
        ModelT1000 t1000 = (ModelT1000) context.getBean("t1000");
        t1000.action();

//        RobotConveyor t1000Conveyor=(RobotConveyor) context.getBean("t1000Conveyor");
//
//        Robot t1=t1000Conveyor.createRobot();
//        Robot t2=t1000Conveyor.createRobot();
//        Robot t3=t1000Conveyor.createRobot();
//
//        System.out.println("t1 "+t1);
//        System.out.println("t2 "+t2);
//        System.out.println("t3 "+t3);
//        t3.action();
//
//        T1000Pool t1000Pool=(T1000Pool) context.getBean("t1000Pool");
//        t1000Pool.actionMap();
    }
}
