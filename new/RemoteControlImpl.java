package ru.sbt.mipt.oop;

import rc.RemoteControl;

import java.util.HashMap;
import java.util.Map;

public class RemoteControlImpl implements RemoteControl {
    private Map<String, Map<String, Command>> commandFromButton = new HashMap<>();

    public RemoteControlImpl(Map<String, Map<String, Command>> commandFromButton) {
        this.commandFromButton = commandFromButton;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (commandFromButton.containsKey(rcId) && commandFromButton.get(rcId).containsKey(buttonCode)) {
            commandFromButton.get(rcId).get(buttonCode).execute();
        }
    }
}
