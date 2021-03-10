package ru.sbt.mipt.oop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class HomeGetterJS implements HomeGetter {

    private final String filename;

    public HomeGetterJS(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome getSmartHome() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        return gson.fromJson(json, SmartHome.class);
    }
}
