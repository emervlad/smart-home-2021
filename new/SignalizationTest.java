package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignalizationTest {

    @Test
    public void stateChanges() {
        Signalization signalization = new Signalization();
        assertTrue(signalization.getState() instanceof DeactiveState);

        signalization.activate("Glina");
        assertTrue(signalization.getState() instanceof ActiveState);
        signalization.deactivate("Clay");
        assertTrue(signalization.getState() instanceof AlarmState);

        signalization.deactivate("Glina");
        assertTrue(signalization.getState() instanceof DeactiveState);
    }

}