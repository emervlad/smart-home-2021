package ru.sbt.mipt.oop;

public class AlarmState implements State{
    private final Signalization signalization;
    private final String code;

    public AlarmState(Signalization signalization, String code) {
        this.signalization = signalization;
        this.code = code;
    }

    @Override
    public void activate(String code) {

    }

    @Override
    public void deactivate(String code) {
        if(code.equals(this.code))
        signalization.setState(new DeactiveState(signalization));
    }

    @Override
    public void switchToAlarm() {

    }
}
