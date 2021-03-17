package ru.sbt.mipt.oop;

public class EventProcessor {
    private final EventCreator sensorEventCreator;

    public EventProcessor(EventCreator sensorEventCreator) {
        this.sensorEventCreator = sensorEventCreator;
    }

    public void processEvent(SmartHome smartHome) {
        SensorEvent event = sensorEventCreator.getNextEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            new DoorProcessor().processEvent(event, smartHome);
            new LightProcessor().processEvent(event, smartHome);
            event = sensorEventCreator.getNextEvent();
        }
    }
}
