package ru.aop.loggins;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NewLogger {

    @Pointcut("execution(* ru.aop.objects.SomeService.*(..))")
    private void allMethod() {
    }

    @AfterReturning(pointcut = "allMethod()", returning = "obj")
    public void printValue(Object obj) {
        System.out.println(obj);
    }

    @Before("allMethod()")
    public void init() {
        System.out.println("init");
    }

    @After("allMethod()")
    public void close() {
        System.out.println("close");
    }
}
