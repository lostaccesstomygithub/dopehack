package me.smokedope2016.dopehack.features.commands;

import me.smokedope2016.dopehack.DopeHack;
import me.smokedope2016.dopehack.features.Feature;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


public abstract class Command
        extends Feature {
    protected String name;
    protected String[] commands;

    public Command(String name) {
        super(name);
        this.name = name;
        this.commands = new String[]{""};
    }

    public Command(String name, String[] commands) {
        super(name);
        this.name = name;
        this.commands = commands;
    }

    public static void sendMessage(String message) {
        Command.sendSilentMessage(DopeHack.commandManager.getClientMessage() + " " + Formatting.GRAY + message);
    }

    public static void sendSilentMessage(String message) {
        if (Command.nullCheck()) {
            return;
        }
        // TODO add silent support ig
        mc.inGameHud.getChatHud().addMessage(Text.literal(message));
    }

    public static String getCommandPrefix() {
        return DopeHack.commandManager.getPrefix();
    }

    public abstract void execute(String[] var1);

    @Override
    public String getName() {
        return this.name;
    }

    public String[] getCommands() {
        return this.commands;
    }
}