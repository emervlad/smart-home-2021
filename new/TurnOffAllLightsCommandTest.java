package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TurnOffAllLightsCommandTest {
    private Door door0 = new Door(true, "0");
    private Door door1 = new Door(false, "1");
    private Door door2 = new Door(true, "2");

    private Light light0 = new Light("0", false);
    private Light light1 = new Light("1", true);
    private Light light2 = new Light("2", true);

    SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(light0), Arrays.asList(door0), "bedroom"),
            new Room(Arrays.asList(light1), Arrays.asList(door1), "kitchen"),
            new Room(Arrays.asList(light2), Arrays.asList(door2), "hall")
    ));

    @Test
    void execute() {
        Command command = new TurnOffAllLightsCommand(smartHome);

        command.execute();

        assertFalse(light0.isOn());
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
    }
}