package me.smokedope2016.dopehack.features.modules.movement;

import me.smokedope2016.dopehack.events.packets.PacketEvent;
import me.smokedope2016.dopehack.mixin.PlayerMoveC2SPacketAccessor;
import meteordevelopment.orbit.EventHandler;

public class NoFall {
    @EventHandler
    private void onSendPacket(PacketEvent.Send event) {
        System.out.println(event);
        ((PlayerMoveC2SPacketAccessor) event.packet).setOnGround(true);
    }
}

