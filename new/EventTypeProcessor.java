package ru.sbt.mipt.oop;

public interface EventTypeProcessor {
    void DoorLightProcessor(SensorEvent event, SmartHome smartHome);
    void printStatus(String id, String name, boolean is);
}
