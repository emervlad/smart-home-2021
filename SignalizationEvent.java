package ru.sbt.mipt.oop;

public class SignalizationEvent extends SensorEvent{
    private final String code;

    public String getCode() {
        return code;
    }

    public SignalizationEvent(SensorEventType type, String objectId, String code) {
        super(type, objectId);
        this.code = code;
    }
}
