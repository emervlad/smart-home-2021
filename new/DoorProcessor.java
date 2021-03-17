package ru.sbt.mipt.oop;


import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorProcessor implements EventTypeProcessor{
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            smartHome.execute(homeComponent -> {
                if (homeComponent instanceof Door) {
                    Door door = (Door) homeComponent;
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                            printStatus(door.getId(), true);
                        } else {
                            door.setOpen(false);
                            printStatus(door.getId(), false);
                        }
                    }
                }
            });
        }
    }


    public void printStatus(String id, boolean is) {
        String f = is ? "opened" : "closed";
        System.out.println("Door " + id + " in room " + " was " + f + ".");
    }


}
