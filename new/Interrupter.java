package ru.sbt.mipt.oop;

public class Interrupter {
    void lightOffAnywhere(SmartHome smartHome) {
        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Light) {
                Light light = (Light) homeComponent;
                light.setOn(false);
                (new DoorProcessor()).printStatus(light.getId(), false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                command.sendCommand();
            }
        });

    }
}
