package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignalizationTest {

    @Test
    public void stateChanges() {
        Signalization signalization = new Signalization();
        assertTrue(signalization.isDeactivate());

        signalization.activate("Glina");
        assertTrue(signalization.isActive());
        signalization.deactivate("Clay");
        assertTrue(signalization.isAlarm());

        signalization.deactivate("Glina");
        assertTrue(signalization.isDeactivate());
    }

}