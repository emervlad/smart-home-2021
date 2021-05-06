package ru.sbt.mipt.oop;

import java.util.Collection;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class Decorator implements EventTypeProcessor {
    private final Collection<EventTypeProcessor> eventHandlers;

    public Decorator(Collection<EventTypeProcessor> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (smartHome.getSignalization().getState() instanceof ActiveState && !((event.getType() == ALARM_ACTIVATE)
                || event.getType() == ALARM_DEACTIVATE)) {
            smartHome.getSignalization().switchToAlarm();
        }
        if (smartHome.getSignalization().getState() instanceof AlarmState && !((event.getType() == ALARM_ACTIVATE)
                || event.getType() == ALARM_DEACTIVATE)) {
            SensorCommand.sendSms();
        } else {
            for (EventTypeProcessor eventHandler : eventHandlers) {
                eventHandler.processEvent(event, smartHome);
            }
        }
    }
}