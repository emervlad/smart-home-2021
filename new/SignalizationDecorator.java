package ru.sbt.mipt.oop;

import java.util.Collection;

public class SignalizationDecorator implements EventTypeProcessor {
    private final Collection<EventTypeProcessor> eventHandlers;

    public SignalizationDecorator(Collection<EventTypeProcessor> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (smartHome.getSignalization().getState() instanceof ActiveState && !(event instanceof SignalizationEvent)) {
            smartHome.getSignalization().switchToAlarm();
        }
        if (smartHome.getSignalization().getState() instanceof AlarmState && !(event instanceof SignalizationEvent)) {
            SensorCommand.sendSms();
        } else {
            for (EventTypeProcessor eventHandler : eventHandlers) {
                eventHandler.processEvent(event, smartHome);
            }
        }
    }

}