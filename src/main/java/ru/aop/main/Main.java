package ru.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.aop.objects.SomeService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("context.xml");
        SomeService service=(SomeService) context.getBean("someService");
        double val=service.getDoubleValue();
    }
}
