package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class HomeConfiguration {
    @Bean
    SmartHome smartHome() throws IOException {
        return new SmartHome(Reader.getSmartHome().getRooms());
    }

    @Bean
    Map<String, SensorEventType> stringToType() {
        return Map.of(
                "LightIsOn", SensorEventType.LIGHT_ON,
                "LightIsOff", SensorEventType.LIGHT_OFF,
                "DoorIsOpen", SensorEventType.DOOR_OPEN,
                "DoorIsClosed", SensorEventType.DOOR_CLOSED
        );
    }

    @Bean
    EventTypeProcessor doorProcessor() {
        return new DoorProcessor();
    }

    @Bean
    EventTypeProcessor lightProcessor() {
        return new LightProcessor();
    }

    @Bean
    EventTypeProcessor hallDoorProcessor() {
        return new HallDoorProcessor();
    }

    @Bean
    EventTypeProcessor signalizationProcessor() {
        return new SignalizationProcessor();
    }

    @Bean
    List<EventTypeProcessor> eventHandlers() {

        return Arrays.asList(doorProcessor(),
                lightProcessor(),
                hallDoorProcessor(),
                signalizationProcessor());
    }

    @Bean
    SignalizationDecorator signalizationDecorator(List<EventTypeProcessor> processorList) {
        return new SignalizationDecorator(processorList);
    }

    @Bean
    SensorEventsManagerMain eventsManager(EventTypeProcessor eventTypeProcessor, SmartHome smartHome) {
        return new SensorEventsManagerMain(eventTypeProcessor, stringToType(), smartHome);
    }

    @Bean
    SensorEventsManager sensorEventsManager(SmartHome smartHome) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(
                eventsManager(signalizationDecorator(eventHandlers()), smartHome));
        return sensorEventsManager;
    }
}
