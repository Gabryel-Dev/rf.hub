package com.redefocus.hub;

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

    }
}
