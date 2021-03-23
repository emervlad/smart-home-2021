package ru.sbt.mipt.oop;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallDoorProcessor implements EventTypeProcessor{
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_CLOSED || event.getType() == DOOR_OPEN) {
            smartHome.execute(homeComponent -> {
                if (homeComponent instanceof Room) {
                    Room room = (Room) homeComponent;
                    if (room.getName().equals("hall")) {
                        room.execute(roomComponent -> {
                            if (roomComponent instanceof Door) {
                                Door door = (Door) roomComponent;
                                if (door.getId().equals(event.getObjectId())) {

                                    if (event.getType() == DOOR_CLOSED) {
                                        (new Interrupter()).lightOffAnywhere(smartHome);
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    }
}
