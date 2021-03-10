package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class DoorProcessor implements EventTypeProcessor{
    @Override
    public void DoorLightProcessor(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            // событие от источника света
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            light.setOn(true);
                            printStatus(light.getId(), room.getName(), true);
                        } else {
                            light.setOn(false);
                            printStatus(light.getId(), room.getName(), false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void printStatus(String id, String name, boolean is) {
            String f = is ? "on" : "off";
            System.out.println("Light " + id + " in room " + name + " was turned " + f + ".");
    }
}
