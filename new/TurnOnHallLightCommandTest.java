package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TurnOnHallLightCommandTest {
    private Door door0 = new Door(true, "0");
    private Door door1 = new Door(false, "1");
    private Door door2 = new Door(true, "2");

    private Light light0 = new Light("0", false);
    private Light light1 = new Light("1", true);
    private Light hallLight = new Light("2", false);

    SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(light0), Arrays.asList(door0), "bedroom"),
            new Room(Arrays.asList(light1), Arrays.asList(door1), "kitchen"),
            new Room(Arrays.asList(hallLight), Arrays.asList(door2), "hall")
    ));

    @Test
    void execute() {
        Command command = new TurnOnHallLightCommand(smartHome);
        hallLight.setOn(false);

        command.execute();

        assertTrue(hallLight.isOn());
    }
}