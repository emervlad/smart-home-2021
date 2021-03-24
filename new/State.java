package ru.sbt.mipt.oop;

public interface State {
    void activate (String code);
    void deactivate (String code);
    void switchToAlarm ();
}
