package ru.sbt.mipt.oop;

public class ActiveStateCommand implements Command {
    private final SmartHome smartHome;
    private final String code;

    public ActiveStateCommand(SmartHome smartHome, String code) {
        this.smartHome = smartHome;
        this.code = code;
    }

    @Override
    public void execute() {
        smartHome.getSignalization().activate(code);
    }
}
