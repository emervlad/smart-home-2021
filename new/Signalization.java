package ru.sbt.mipt.oop;

public class Signalization implements State {
    private State state = new DeactiveState(this);

    boolean isDeactivate() {
        return (this.state instanceof DeactiveState);
    }

    boolean isActive() {
        return (this.state instanceof ActiveState);
    }

    boolean isAlarm() {
        return (this.state instanceof AlarmState);
    }

    void setState(State state) {
        this.state = state;
    }

    @Override
    public void activate(String code) {
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        state.deactivate(code);
    }

    @Override
    public void switchToAlarm() {
        state.switchToAlarm();
    }

}
