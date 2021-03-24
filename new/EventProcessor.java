package ru.sbt.mipt.oop;

import java.util.List;

public class EventProcessor {
    private final EventCreator sensorEventCreator;
    private final List<EventTypeProcessor> processorList;

    public EventProcessor(EventCreator sensorEventCreator, List<EventTypeProcessor> processorList) {
        this.sensorEventCreator = sensorEventCreator;
        this.processorList = processorList;
    }

    public void processEvent(SmartHome smartHome) {
        SensorEvent event = sensorEventCreator.getNextEvent();
        Decorator decorator = new Decorator(processorList);
        while (event != null) {
            System.out.println("Got event: " + event);
            decorator.processEvent(event, smartHome);
            event = sensorEventCreator.getNextEvent();
        }
    }
}
