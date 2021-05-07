package ru.sbt.mipt.oop;

public class TurnOnHallLightCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Room) {
                Room room = (Room) homeComponent;

                if (room.getName().equals("hall")) {
                    room.execute(roomObject -> {
                        if (roomObject instanceof Light) {
                            Light light = (Light) roomObject;
                            light.setOn(true);
                        }
                    });
                }

            }
        });
    }
}