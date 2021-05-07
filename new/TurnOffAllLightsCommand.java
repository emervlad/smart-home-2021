package ru.sbt.mipt.oop;

public class TurnOffAllLightsCommand implements Command {
    private final SmartHome smartHome;

    public TurnOffAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Light) {
                Light light = (Light) homeComponent;
                light.setOn(false);
            }
        });
    }
}
