package ru.sbt.mipt.oop;

public class SensorCommand {
    private final CommandType type;
    private final String objectId;

    public SensorCommand(CommandType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "SensorCommand{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }

    public void sendCommand() {
        System.out.println("Pretent we're sending command " + this.toString());
    }

    public void sendSms() {System.out.println("Sending sms");}
}
