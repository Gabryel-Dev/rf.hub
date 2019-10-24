package com.redefocus.hub.listeners.general;

import com.redefocus.hub.items.LobbyItem;
import com.redefocus.hub.managers.SpawnManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;

public class PlayerJoinTeleportToSpawnListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerInventory playerInventory = player.getInventory();

        playerInventory.clear();

        player.teleport(SpawnManager.getSpawn());

        for (LobbyItem lobbyItem : LobbyItem.values()) {
            playerInventory.setItem(
                    lobbyItem.getSlot(),
                    lobbyItem.getIcon().clone()
            );
        }
    }
}
