package me.smokedope2016;

import me.smokedope2016.DiscordRPC;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.metadata.ModMetadata;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class dopehack implements ModInitializer, ClientModInitializer{
    public static final String MOD_ID = "dopehack";
    public static final ModMetadata MOD_META;
    public static final String NAME;
    public static final String VERSION;
    public static final String GAME_VERSION;
    public static final Logger LOG;
    
    static {
        MOD_META = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow().getMetadata();

        NAME = MOD_META.getName();
        VERSION = MOD_META.getVersion().getFriendlyString();
        GAME_VERSION = SharedConstants.getGameVersion().getName();

        LOG = LoggerFactory.getLogger(NAME);
    }
    @Override
    public void onInitialize() {
    }

    @Override
    public void onInitializeClient() {
        DiscordRPC.init();
    }

}
