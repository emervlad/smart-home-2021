package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RemoteControlImplTest {
    private Door door0 = new Door(true, "0");
    private Door door1 = new Door(false, "1");
    private Door hallDoor = new Door(true, "2");

    private Light light0 = new Light("0", false);
    private Light light1 = new Light("1", true);
    private Light hallLight = new Light("2", false);

    SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(light0), Arrays.asList(door0), "bedroom"),
            new Room(Arrays.asList(light1), Arrays.asList(door1), "kitchen"),
            new Room(Arrays.asList(hallLight), Arrays.asList(hallDoor), "hall")
    ));

    @Test
    void onButtonPressed() {
        String code = "123";
        RemoteControlImpl remoteControl = new RemoteControlImpl(Map.of("id1", Map.of(
                "A", new CloseFrontDoorCommand(smartHome),
                "B", new ActiveStateCommand(smartHome, code),
                "C", new AlarmStateCommand(smartHome),
                "1", new TurnOffAllLightsCommand(smartHome),
                "2", new TurnOnAllLightsCommand(smartHome),
                "3", new TurnOnHallLightCommand(smartHome)
        )));

        remoteControl.onButtonPressed("A", "id1");
        assertFalse(hallDoor.isOpen());

        remoteControl.onButtonPressed("B", "id1");
        assertTrue(smartHome.getSignalization().isActive());

        remoteControl.onButtonPressed("C", "id1");
        assertTrue(smartHome.getSignalization().isAlarm());
        smartHome.getSignalization().deactivate(code);

        remoteControl.onButtonPressed("1", "id1");
        assertFalse(light0.isOn());
        assertFalse(light1.isOn());
        assertFalse(hallLight.isOn());

        remoteControl.onButtonPressed("2", "id1");
        assertTrue(light0.isOn());
        assertTrue(light1.isOn());
        assertTrue(hallLight.isOn());

        hallLight.setOn(false);
        remoteControl.onButtonPressed("3", "id1");
        assertTrue(hallLight.isOn());
    }
}