package ru.sbt.mipt.oop;

public class CloseFrontDoorCommand implements Command {
    private final SmartHome smartHome;

    public CloseFrontDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Room) {
                Room room = (Room) homeComponent;

                if (room.getName().equals("hall")) {
                    room.execute(roomObject -> {
                        if (roomObject instanceof Door) {
                            Door door = (Door) roomObject;
                            door.setOpen(false);
                        }
                    });
                }

            }
        });
    }
}
