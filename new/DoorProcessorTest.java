package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import static ru.sbt.mipt.oop.SensorEventType.*;
import static org.junit.jupiter.api.Assertions.*;

class DoorProcessorTest {
    private final Door door0 = new Door(true, "0");
    private final Door door1 = new Door(false, "1");
    private final Door door2 = new Door(true, "2");

    private final Light light0 = new Light("0", false);
    private final Light light1 = new Light("1", true);
    private final Light light2 = new Light("2", false);

    SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Collections.singletonList(light0), Collections.singletonList(door0), "bedroom"),
            new Room(Collections.singletonList(light1), Collections.singletonList(door1), "kitchen"),
            new Room(Collections.singletonList(light2), Collections.singletonList(door2), "hall")
    ));

    private final DoorProcessor doorProcessor = new DoorProcessor();

    @Test
    public void doorClose() {
        doorProcessor.processEvent(new SensorEvent(DOOR_CLOSED, "0"), smartHome);
        assertFalse(light0.isOn());
        assertTrue(light1.isOn());
        assertFalse(light2.isOn());

        assertFalse(door0.isOpen());
        assertFalse(door1.isOpen());
        assertTrue(door2.isOpen());
    }

    @Test
    public void doorOpen() {
        doorProcessor.processEvent(new SensorEvent(DOOR_OPEN, "1"), smartHome);
        assertFalse(light0.isOn());
        assertTrue(light1.isOn());
        assertFalse(light2.isOn());

        assertTrue(door0.isOpen());
        assertTrue(door1.isOpen());
        assertTrue(door2.isOpen());
    }

    @Test
    public void doorWithoutID() {
        doorProcessor.processEvent(new SensorEvent(DOOR_CLOSED, "10"), smartHome);
        assertFalse(light0.isOn());
        assertTrue(light1.isOn());
        assertFalse(light2.isOn());

        assertTrue(door0.isOpen());
        assertFalse(door1.isOpen());
        assertTrue(door2.isOpen());
    }
}
