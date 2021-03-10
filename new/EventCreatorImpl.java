package ru.sbt.mipt.oop;

public class EventCreatorImpl implements EventCreator {
    public SensorEvent getNextEvent() {
        // представьте, что мы получаем события из физического мира, но здесь мы собираемся просто генерировать некоторые случайные события
        if (Math.random() < 0.05) return null; // null означает конец потока событий
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
