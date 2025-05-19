package me.smokedope2016.dopehack.features.commands.impl;

import me.smokedope2016.dopehack.DopeHack;
import me.smokedope2016.dopehack.features.commands.Command;
import net.minecraft.util.Formatting;

public class FriendCommand
        extends Command {
    public FriendCommand() {
        super("friend", new String[]{"<add/del/name/clear>", "<name>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            if (DopeHack.friendManager.getFriends().isEmpty()) {
                FriendCommand.sendMessage("Friend list empty D:.");
            } else {
                StringBuilder f = new StringBuilder("Friends: ");
                for (String friend : DopeHack.friendManager.getFriends()) {
                    try {
                        f.append(friend).append(", ");
                    } catch (Exception exception) {
                    }
                }
                FriendCommand.sendMessage(f.toString());
            }
            return;
        }
        if (commands.length == 2) {
            if (commands[0].equals("reset")) {
                DopeHack.friendManager.getFriends().clear();
                FriendCommand.sendMessage("Friends got reset.");
                return;
            }
            FriendCommand.sendMessage(commands[0] + (DopeHack.friendManager.isFriend(commands[0]) ? " is friended." : " isn't friended."));
            return;
        }
        if (commands.length >= 2) {
            switch (commands[0]) {
                case "add" -> {
                    DopeHack.friendManager.addFriend(commands[1]);
                    FriendCommand.sendMessage(Formatting.GREEN + commands[1] + " has been friended");
                    return;
                }
                case "del", "remove" -> {
                    DopeHack.friendManager.removeFriend(commands[1]);
                    FriendCommand.sendMessage(Formatting.RED + commands[1] + " has been unfriended");
                    return;
                }
            }
            FriendCommand.sendMessage("Unknown Command, try friend add/del (name)");
        }
    }
}