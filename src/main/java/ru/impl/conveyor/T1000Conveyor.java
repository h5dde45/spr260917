package ru.impl.conveyor;

import ru.interfaces.Robot;
import ru.interfaces.RobotConveyor;

public abstract class T1000Conveyor implements RobotConveyor{
    @Override
    public abstract Robot createRobot();
}
