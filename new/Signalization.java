package ru.sbt.mipt.oop;

public class Signalization implements State {
    private State state = new DeactiveState(this);

    public State getState() {
        return state;
    }

    public void setState(State state) {
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
