package com.redefocus.hub.managers;

import com.redefocus.hub.FocusHub;
import com.redefocus.hub.chat.managers.ChatManager;
import com.redefocus.hub.combat.manager.CombatPlayerManager;
import com.redefocus.hub.commands.player.TellCommand;
import com.redefocus.hub.commands.staff.*;
import com.redefocus.hub.scoreboard.manager.ScoreboardManager;
import com.redefocus.hub.scoreboard.runnable.ScoreboardRefreshRunnable;
import com.redefocus.hub.servers.manager.ServerManager;
import com.redefocus.hub.servers.runnable.ServerRefreshRunnable;
import com.redefocus.hub.tablist.managers.TablistManager;
import com.redefocus.hub.tags.managers.TagsManager;
import com.redefocus.hub.util.ClassGetter;
import com.redefocus.hub.util.inventory.InventoryBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class StartManager {
    private static TagsManager tagsManager;

    public StartManager() {
        new ListenerManager();
        new CommandManager();

        new ChatManager();
        new SpawnManager();
        new TablistManager();
        new ServerManager();
        new CombatPlayerManager();
        new ScoreboardManager();
        new RunnableManager();

        StartManager.tagsManager = new TagsManager();
    }

    public static TagsManager getTagsManager() {
        return StartManager.tagsManager;
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
        this.register("tell", new TellCommand());
    }

    private void register(String name, CommandExecutor command) {
        FocusHub.getInstance().getCommand(name).setExecutor(command);
    }
}

class RunnableManager {
    RunnableManager() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(
                FocusHub.getInstance(),
                new ServerRefreshRunnable(),
                0L,
                20L * 5
        );
        Bukkit.getScheduler().scheduleSyncRepeatingTask(
                FocusHub.getInstance(),
                new ScoreboardRefreshRunnable(),
                0L,
                20L*8
        );
    }
}