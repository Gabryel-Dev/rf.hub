package com.redefocus.hub.combat.listeners;

import com.redefocus.hub.combat.manager.CombatPlayerManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class CombatPlayerDamageListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (entity.getType() != EntityType.PLAYER) return;

        Player player = (Player) entity;

        if (!CombatPlayerManager.isBattling(player)) event.setCancelled(true);
    }
}
