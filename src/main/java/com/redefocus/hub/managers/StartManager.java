package com.redefocus.hub.managers;

import com.redefocus.hub.FocusHub;
import com.redefocus.hub.chat.managers.ChatManager;
import com.redefocus.hub.combat.manager.CombatPlayerManager;
import com.redefocus.hub.commands.staff.*;
import com.redefocus.hub.serverselector.manager.ServerManager;
import com.redefocus.hub.tablist.managers.TablistManager;
import com.redefocus.hub.util.ClassGetter;
import com.redefocus.hub.util.inventory.InventoryBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class StartManager {
    public StartManager() {
        new ListenerManager();
        new CommandManager();

        new ChatManager();
        new SpawnManager();
        new TablistManager();
        new ServerManager();
        new CombatPlayerManager();
    }
}
class ListenerManager {
    ListenerManager() {
        ClassGetter.getClassesForPackage(FocusHub.getInstance(), "com.redefocus").forEach(clazz -> {
            if (Listener.class.isAssignableFrom(clazz) && !clazz.equals(InventoryBuilder.class)) {
                try {
                    Listener listener = (Listener) clazz.newInstance();
                    Bukkit.getPluginManager().registerEvents(listener, FocusHub.getInstance());
                } catch (InstantiationException | IllegalAccessException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}

class CommandManager {
    CommandManager() {
        this.register("gamemode", new GamemodeCommand());
        this.register("fly", new FlyCommand());
        this.register("ping", new PingCommand());
        this.register("setspawn", new SetSpawnCommand());
        this.register("teleport", new TeleportCommand());
    }

    private void register(String name, CommandExecutor command) {
        FocusHub.getInstance().getCommand(name).setExecutor(command);
    }
}