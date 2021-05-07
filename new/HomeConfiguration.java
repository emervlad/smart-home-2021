package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControlRegistry;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class HomeConfiguration {
    @Bean
    SmartHome smartHome() throws IOException {
        return new SmartHome((new HomeReaderJS("smart-home-1.js")).getSmartHome().getRooms());
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
    String codeForSignalization() {
        // there may be some logic here for get random code
        return "123456";
    }

    @Bean
    CloseFrontDoorCommand closeFrontDoorCommand(SmartHome smartHome) {
        return new CloseFrontDoorCommand(smartHome);
    }

    @Bean
    ActiveStateCommand activeStateCommand(SmartHome smartHome) {
        return new ActiveStateCommand(smartHome, codeForSignalization());
    }

    @Bean
    AlarmStateCommand alarmStateCommand(SmartHome smartHome) {
        return new AlarmStateCommand(smartHome);
    }

    @Bean
    TurnOffAllLightsCommand turnOffAllLightsCommand(SmartHome smartHome) {
        return new TurnOffAllLightsCommand(smartHome);
    }

    @Bean
    TurnOnAllLightsCommand turnOnAllLightsCommand(SmartHome smartHome) {
        return new TurnOnAllLightsCommand(smartHome);
    }

    @Bean
    TurnOnHallLightCommand turnOnHallLightCommand(SmartHome smartHome) {
        return new TurnOnHallLightCommand(smartHome);
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry(SmartHome smartHome) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl(smartHome), "id1");
        return remoteControlRegistry;
    }

    @Bean
    RemoteControlImpl remoteControl(SmartHome smartHome) {
        return new RemoteControlImpl(Map.of("id1", Map.of(
                "A", closeFrontDoorCommand(smartHome),
                "B", activeStateCommand(smartHome),
                "C", alarmStateCommand(smartHome),
                "1", turnOffAllLightsCommand(smartHome),
                "2", turnOnAllLightsCommand(smartHome),
                "3", turnOnHallLightCommand(smartHome)
        )));
    }

    @Bean
    SensorEventsManager sensorEventsManager(SmartHome smartHome) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(
                eventsManager(signalizationDecorator(eventHandlers()), smartHome));
        return sensorEventsManager;
    }
}
