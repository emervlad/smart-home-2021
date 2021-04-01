package ru.sbt.mipt.oop;

import java.util.Collection;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class SignalizationDecorator implements EventTypeProcessor {
    private final Collection<EventTypeProcessor> eventHandlers;

    public SignalizationDecorator(Collection<EventTypeProcessor> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (smartHome.getSignalization().isActive() && !((event.getType() == ALARM_ACTIVATE)
                || event.getType() == ALARM_DEACTIVATE)) {
            smartHome.getSignalization().switchToAlarm();
        }
        if (smartHome.getSignalization().isAlarm() && !((event.getType() == ALARM_ACTIVATE)
                || event.getType() == ALARM_DEACTIVATE)) {
            SensorCommand.sendSms();
        } else {
            for (EventTypeProcessor eventHandler : eventHandlers) {
                eventHandler.processEvent(event, smartHome);
            }
        }
    }
}