package ru.sbt.mipt.oop;

public class TurnOnAllLightsCommand implements Command{
    private final SmartHome smartHome;

    public TurnOnAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Light) {
                Light light = (Light) homeComponent;
                light.setOn(true);
            }
        });
    }
}
