package ru.ioc.interfaces;

import java.util.Collection;
import java.util.Map;

public interface RoborPool {
    Collection<Robot> getRobotPool();
    Map<String,Robot> getRobotMap();
}
