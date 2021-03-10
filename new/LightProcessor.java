package ru.sbt.mipt.oop;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class LightProcessor implements EventTypeProcessor{
    @Override
    public void DoorLightProcessor(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                            printStatus(door.getId(), room.getName(), true);
                        } else {
                            door.setOpen(false);
                            printStatus(door.getId(), room.getName(), false);
                            lightOffAnywhere(smartHome.getRooms(), room.getName());
                        }
                    }
                }
            }
        }
    }

    public void printStatus(String id, String name, boolean is) {
        String f = is ? "opened" : "closed";
        System.out.println("Door " + id + " in room " + name + " was " + f + ".");
    }

    private void lightOffAnywhere(Collection <Room> rooms, String roomName) {
        if (roomName.equals("hall")) {
            for (Room homeRoom : rooms) {
                for (Light light : homeRoom.getLights()) {
                    light.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    command.sendCommand();
                }
            }
        }
    }
}
