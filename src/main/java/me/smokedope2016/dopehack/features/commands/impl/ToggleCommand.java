package me.smokedope2016.dopehack.features.commands.impl;

import me.smokedope2016.dopehack.DopeHack;
import me.smokedope2016.dopehack.features.commands.Command;
import me.smokedope2016.dopehack.features.modules.Module;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("toggle", new String[] {"<module>"});
    }

    @Override public void execute(String[] var1) {
        if (var1.length < 1 || var1[0] == null) {
            notFound();
            return;
        }
        Module mod = DopeHack.moduleManager.getModuleByName(var1[0]);
        if (mod == null) {
            notFound();
            return;
        }
        mod.toggle();
    }

    private void notFound() {
        sendMessage("Module is not found.");
    }
}
