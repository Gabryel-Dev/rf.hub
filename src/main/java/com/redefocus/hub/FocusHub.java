package com.redefocus.hub;

import com.redefocus.hub.managers.StartManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FocusHub extends JavaPlugin {
    private static FocusHub instance;

    public FocusHub() {
        FocusHub.instance = this;
    }

    public static FocusHub getInstance() {
        return FocusHub.instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        new StartManager();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }
}
