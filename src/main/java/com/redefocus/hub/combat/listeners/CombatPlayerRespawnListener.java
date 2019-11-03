package com.redefocus.hub.combat.listeners;

import com.redefocus.hub.combat.data.CombatPlayer;
import com.redefocus.hub.combat.manager.CombatPlayerManager;
import com.redefocus.hub.managers.SpawnManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class CombatPlayerRespawnListener implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        CombatPlayer combatPlayer = CombatPlayerManager.get(player.getUniqueId());

        if (combatPlayer != null) combatPlayer.end();

        Location location = SpawnManager.getSpawn();

        player.teleport(location);
    }
}
