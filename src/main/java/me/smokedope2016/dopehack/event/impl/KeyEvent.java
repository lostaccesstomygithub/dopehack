package me.smokedope2016.dopehack.event.impl;

import me.smokedope2016.dopehack.event.Event;

public class KeyEvent extends Event {
    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
