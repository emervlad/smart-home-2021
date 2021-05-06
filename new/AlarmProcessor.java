package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class AlarmProcessor implements EventTypeProcessor{
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE) {
            smartHome.execute(homeObject -> {
                if (homeObject instanceof Signalization) {
                    Signalization signalization = (Signalization) homeObject;
                    if (event.getType() == ALARM_ACTIVATE) {
                        signalization.activate(((SignalizationEvent) event).getCode());
                    } else {
                        signalization.deactivate(((SignalizationEvent) event).getCode());
                    }
                }
            });
        }
    }
}
