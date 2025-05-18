package me.smokedope2016.dopehack;

import me.smokedope2016.dopehack.DopeHack;
import meteordevelopment.discordipc.DiscordIPC;
import meteordevelopment.discordipc.RichPresence;

public class DiscordRPC {
    private static final RichPresence rpc = new RichPresence();

    public static void init() {
        DiscordIPC.start(1372541350209261669L, null);
        rpc.setStart(System.currentTimeMillis() / 1000L);
        String largeText = "%s %s for %s".formatted(DopeHack.NAME, DopeHack.VERSION, DopeHack.GAME_VERSION);
        rpc.setDetails("probably on 6b6t");
        rpc.setLargeImage("whoknows", largeText);
        DiscordIPC.setActivity(rpc);
    }
}
