package ru.impl.pool;

import ru.interfaces.RoborPool;
import ru.interfaces.Robot;

import java.util.Collection;
import java.util.Map;

public class T1000Pool implements RoborPool {

    private Collection<Robot> robotCollection;
    private Map<String, Robot> robotMap;

    public T1000Pool(Map<String, Robot> robotMap) {
        this.robotMap = robotMap;
    }

    public void setRobotCollection(Collection<Robot> robotCollection) {
        this.robotCollection = robotCollection;
    }

    @Override
    public Collection<Robot> getRobotPool() {
        return null;
    }

    @Override
    public Map<String, Robot> getRobotMap() {
        return null;
    }

    public void action() {
        for (Robot robot : robotCollection) {
            robot.action();
        }
    }

    public void actionMap() {
        for (Map.Entry<String, Robot> entry : robotMap.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().action();
        }
    }
}
