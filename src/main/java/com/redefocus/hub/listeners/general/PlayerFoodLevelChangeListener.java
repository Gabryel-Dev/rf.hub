package com.redefocus.hub.listeners.general;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerFoodLevelChangeListener implements Listener {
    @EventHandler
    public void onChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
