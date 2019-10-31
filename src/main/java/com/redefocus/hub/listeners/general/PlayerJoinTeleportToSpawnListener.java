package com.redefocus.hub.listeners.general;

import com.redefocus.hub.items.LobbyItem;
import com.redefocus.hub.managers.SpawnManager;
import com.redefocus.hub.util.inventory.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerJoinTeleportToSpawnListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerInventory playerInventory = player.getInventory();

        playerInventory.setHelmet(null);
        playerInventory.setChestplate(null);
        playerInventory.setLeggings(null);
        playerInventory.setBoots(null);

        playerInventory.clear();

        player.teleport(SpawnManager.getSpawn());

        for (LobbyItem lobbyItem : LobbyItem.values()) {
            ItemStack icon = lobbyItem.getIcon().clone();

            Item item = new Item(icon);

            if (lobbyItem == LobbyItem.SHOP) item.owner(player.getName());

            playerInventory.setItem(
                    lobbyItem.getSlot(),
                    item.build()
            );
        }
    }
}
