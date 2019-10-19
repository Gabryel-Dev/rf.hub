package com.redefocus.hub.listeners.protection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class ProtectionWorldEntitySpawnListener implements Listener {
    @EventHandler
    public void onSpawn(EntitySpawnEvent event) { event.setCancelled(true); }
}
