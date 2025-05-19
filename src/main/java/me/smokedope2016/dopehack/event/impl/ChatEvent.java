package me.smokedope2016.dopehack.event.impl;

import me.smokedope2016.dopehack.event.Event;

public class ChatEvent extends Event {
    private final String content;

    public ChatEvent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return content;
    }
}
