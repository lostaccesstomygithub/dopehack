package me.smokedope2016.dopehack.event.impl;

import me.smokedope2016.dopehack.event.Event;
import me.smokedope2016.dopehack.event.Stage;

public class UpdateWalkingPlayerEvent extends Event {
    private final Stage stage;

    public UpdateWalkingPlayerEvent(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
