package me.smokedope2016.dopehack.features.modules.player;

import com.google.common.eventbus.Subscribe;
import me.smokedope2016.dopehack.event.impl.PacketEvent;
import me.smokedope2016.dopehack.features.modules.Module;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

public class Velocity extends Module {
    public Velocity() {
        super("Velocity", "Removes velocity from explosions and entities", Category.PLAYER, true, false, false);
    }

    @Subscribe private void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof EntityVelocityUpdateS2CPacket || event.getPacket() instanceof ExplosionS2CPacket) event.cancel();
    }
}
