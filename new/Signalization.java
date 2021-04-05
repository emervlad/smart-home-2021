package ru.sbt.mipt.oop;

public class Signalization implements Actionable {
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

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void switchToAlarm() {
        state.switchToAlarm();
    }

    @Override
    public void execute(Action action) {
        action.apply(this);
    }
}
