package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventProcessor {
    private final EventCreator sensorEventCreator;

    public EventProcessor(EventCreator sensorEventCreator) {
        this.sensorEventCreator = sensorEventCreator;
    }

    public void processEvent(SmartHome smartHome) {
        SensorEvent event = sensorEventCreator.getNextEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            new LightProcessor().DoorLightProcessor(event, smartHome);
            new DoorProcessor().DoorLightProcessor(event, smartHome);
            event = sensorEventCreator.getNextEvent();
        }
    }
}
