package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;

import java.util.Map;

public class SensorEventsManagerMain implements com.coolcompany.smarthome.events.EventHandler {

    private final EventTypeProcessor eventTypeProcessor;
    private final Map<String, SensorEventType> stringToType;
    private final SmartHome smartHome;

    public SensorEventsManagerMain(EventTypeProcessor eventTypeProcessor, Map<String, SensorEventType> stringToType, SmartHome smartHome) {
        this.eventTypeProcessor = eventTypeProcessor;
        this.stringToType = stringToType;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEventType sensorEventType = stringToType.get(event.getEventType());
        if (sensorEventType != null) {
            SensorEvent sensorEvent = new SensorEvent(sensorEventType, event.getObjectId());
            eventTypeProcessor.processEvent(sensorEvent, smartHome);
        }
    }
}
