package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightProcessor implements EventTypeProcessor{
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            // событие от источника света
            smartHome.execute(homeComponent -> {
                if (homeComponent instanceof Light) {
                Light light = (Light) homeComponent;
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn(true);
                        printStatus(light.getId(), true);
                    } else {
                        light.setOn(false);
                        printStatus(light.getId(), false);
                    }
                }
            }
            });
        }
    }


    public void printStatus(String id, boolean is) {
            String f = is ? "on" : "off";
            System.out.println("Light " + id + " in room " + " was turned " + f + ".");
    }
}
