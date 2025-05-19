package me.smokedope2016.dopehack.manager;

import com.google.common.eventbus.Subscribe;
import me.smokedope2016.dopehack.DopeHack;
import me.smokedope2016.dopehack.event.Stage;
import me.smokedope2016.dopehack.event.impl.*;
import me.smokedope2016.dopehack.features.Feature;
import me.smokedope2016.dopehack.features.commands.Command;
import me.smokedope2016.dopehack.util.models.Timer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.Formatting;

public class EventManager extends Feature {
    private final Timer logoutTimer = new Timer();

    public void init() {
        EVENT_BUS.register(this);
    }

    public void onUnload() {
        EVENT_BUS.unregister(this);
    }

    @Subscribe
    public void onUpdate(UpdateEvent event) {
        mc.getWindow().setTitle("DopeHack 0.0.3");
        if (!fullNullCheck()) {
//            DopeHack.inventoryManager.update();
            DopeHack.moduleManager.onUpdate();
            DopeHack.moduleManager.sortModules(true);
            onTick();
//            if ((HUD.getInstance()).renderingMode.getValue() == HUD.RenderingMode.Length) {
//                DopeHack.moduleManager.sortModules(true);
//            } else {
//                DopeHack.moduleManager.sortModulesABC();
//            }
        }
    }

    public void onTick() {
        if (fullNullCheck())
            return;
        DopeHack.moduleManager.onTick();
        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == null || player.getHealth() > 0.0F)
                continue;
            EVENT_BUS.post(new DeathEvent(player));
//            PopCounter.getInstance().onDeath(player);
        }
    }

    @Subscribe
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
        if (fullNullCheck())
            return;
        if (event.getStage() == Stage.PRE) {
            DopeHack.speedManager.updateValues();
            DopeHack.rotationManager.updateRotations();
            DopeHack.positionManager.updatePosition();
        }
        if (event.getStage() == Stage.POST) {
            DopeHack.rotationManager.restoreRotations();
            DopeHack.positionManager.restorePosition();
        }
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        DopeHack.serverManager.onPacketReceived();
        if (event.getPacket() instanceof WorldTimeUpdateS2CPacket)
            DopeHack.serverManager.update();
    }

    @Subscribe
    public void onWorldRender(Render3DEvent event) {
        DopeHack.moduleManager.onRender3D(event);
    }

    @Subscribe public void onRenderGameOverlayEvent(Render2DEvent event) {
        DopeHack.moduleManager.onRender2D(event);
    }

    @Subscribe public void onKeyInput(KeyEvent event) {
        DopeHack.moduleManager.onKeyPressed(event.getKey());
    }

    @Subscribe public void onChatSent(ChatEvent event) {
        if (event.getMessage().startsWith(Command.getCommandPrefix())) {
            event.cancel();
            try {
                if (event.getMessage().length() > 1) {
                    DopeHack.commandManager.executeCommand(event.getMessage().substring(Command.getCommandPrefix().length() - 1));
                } else {
                    Command.sendMessage("Please enter a command.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendMessage(Formatting.RED + "An error occurred while running this command. Check the log!");
            }
        }
    }
}