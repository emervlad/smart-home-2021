package ru.sbt.mipt.oop;

import java.util.logging.Logger;

public class SignalizationProcessor implements EventTypeProcessor{
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == SensorEventType.ALARM_ACTIVATE && event instanceof SignalizationEvent) {
            handleSignalization(smartHome, (SignalizationEvent) event, true);
        }

        if (event.getType() == SensorEventType.ALARM_DEACTIVATE && event instanceof SignalizationEvent) {
            handleSignalization(smartHome, (SignalizationEvent) event, false);
        }
    }

    private static void handleSignalization(SmartHome smartHome, SignalizationEvent event, boolean isActive) {
        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Signalization) {
                Signalization signalization = (Signalization) homeComponent;
                if (isActive) {
                    signalization.activate(event.getCode());
                } else {
                    signalization.deactivate(event.getCode());
                }
            }
        });
    }
}
