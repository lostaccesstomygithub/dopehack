package me.smokedope2016.dopehack.features.commands.impl;

import me.smokedope2016.dopehack.DopeHack;
import me.smokedope2016.dopehack.features.commands.Command;
import net.minecraft.util.Formatting;

public class PrefixCommand
        extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage(Formatting.GREEN + "Current prefix is " + DopeHack.commandManager.getPrefix());
            return;
        }
        DopeHack.commandManager.setPrefix(commands[0]);
        Command.sendMessage("Prefix changed to " + Formatting.GRAY + commands[0]);
    }
}