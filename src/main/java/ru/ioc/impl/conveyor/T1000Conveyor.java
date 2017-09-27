package ru.ioc.impl.conveyor;

import ru.ioc.interfaces.Robot;
import ru.ioc.interfaces.RobotConveyor;

public abstract class T1000Conveyor implements RobotConveyor{
    @Override
    public abstract Robot createRobot();
}
