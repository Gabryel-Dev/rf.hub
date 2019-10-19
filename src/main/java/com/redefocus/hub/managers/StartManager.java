package com.redefocus.hub.managers;

import com.redefocus.hub.FocusHub;
import com.redefocus.hub.chat.managers.ChatManager;
import com.redefocus.hub.tablist.managers.TablistManager;
import com.redefocus.hub.util.ClassGetter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class StartManager {
    public StartManager() {
        new ListenerManager();
        new CommandManager();

        new ChatManager();
        new SpawnManager();
        new TablistManager();
    }
}
class ListenerManager {
    ListenerManager() {
        ClassGetter.getClassesForPackage(FocusHub.getInstance(), "com.redefocus").forEach(clazz -> {
            if (Listener.class.isAssignableFrom(clazz)) {
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

    }
}