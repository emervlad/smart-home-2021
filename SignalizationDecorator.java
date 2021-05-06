package ru.sbt.mipt.oop;

import java.util.List;
import java.util.logging.Logger;

public class SignalizationDecorator implements EventTypeProcessor {
    private static final Logger logger = Logger.getLogger(SignalizationDecorator.class.getName());

    private final List<EventTypeProcessor> eventTypeProcessorList;

    public SignalizationDecorator(List<EventTypeProcessor> eventTypeProcessorList) {
        this.eventTypeProcessorList = eventTypeProcessorList;
    }

    @Override
    public void processEvent(SensorEvent event ,SmartHome smartHome) {
        if (isAlarmEvent(event)) {
            return;
        }

        if (smartHome.getSignalization().isAlarm()) {
            logger.fine(("Sensor detection."));
            logger.info("Sending sms.");
            return;
        }

        eventTypeProcessorList.forEach(handler -> handler.processEvent(event, smartHome));

        if (smartHome.getSignalization().isActive()) {
            smartHome.getSignalization().switchToAlarm();
            logger.fine("Sensor detection.");
            logger.info("Sending sms.");
        }
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_DEACTIVATE || event.getType() == SensorEventType.ALARM_ACTIVATE;
    }
}