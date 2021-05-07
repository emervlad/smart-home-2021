package ru.sbt.mipt.oop;

public class AlarmStateCommand implements Command {
    private final SmartHome smartHome;

    public AlarmStateCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getSignalization().switchToAlarm();
    }
}
