package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        Application application = new Application(new EventProcessor(new EventCreatorImpl()), new HomeReaderJS("smart-home-1.js"));
        application.run();
    }

    private final EventProcessor processor;
    private final HomeReader homeGetter;

    public Application(EventProcessor processor, HomeReader homeGetter) {
        this.processor = processor;
        this.homeGetter = homeGetter;
    }

    public void run() throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = homeGetter.getSmartHome();
        processor.processEvent(smartHome);
    }
}