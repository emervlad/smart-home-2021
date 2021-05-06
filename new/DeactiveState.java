package ru.sbt.mipt.oop;

public class DeactiveState implements State{
    private final Signalization signalization;

    public DeactiveState(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {
        signalization.setState(new ActiveState(signalization, code));
    }

    @Override
    public void deactivate(String code) {

    }

    @Override
    public void switchToAlarm() {

    }
}
