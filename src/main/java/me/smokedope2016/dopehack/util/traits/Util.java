package me.smokedope2016.dopehack.util.traits;

import com.google.common.eventbus.EventBus;
import net.minecraft.client.MinecraftClient;

public interface Util {
    MinecraftClient mc = MinecraftClient.getInstance();
    EventBus EVENT_BUS = new EventBus();
}
