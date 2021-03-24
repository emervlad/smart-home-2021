package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.Arrays;

public class Application {

    public static void main(String... args) throws IOException {
        Application application = new Application(new EventProcessor(new EventCreatorImpl(), Arrays.asList(new LightProcessor(), new DoorProcessor(), new HallDoorProcessor(), new AlarmProcessor())), new HomeReaderJS("smart-home-1.js"));
        application.run();
    }

    private final EventProcessor processor;
    private final HomeReader homeReader;

    public Application(EventProcessor processor, HomeReader homeReader) {
        this.processor = processor;
        this.homeReader = homeReader;
    }

    public void run() throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = homeReader.getSmartHome();
        processor.processEvent(smartHome);
    }
}