package ru.sbt.mipt.oop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class HomeReaderJS implements HomeReader {

    private final String filename;

    public HomeReaderJS(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome getSmartHome() {
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            System.out.println("Error in method \"readAllBytes\"");
        }
        return gson.fromJson(json , SmartHome.class);
    }
}
