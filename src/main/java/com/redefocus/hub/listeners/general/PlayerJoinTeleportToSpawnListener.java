package com.redefocus.hub.listeners.general;

import com.redefocus.hub.items.LobbyItem;
import com.redefocus.hub.managers.SpawnManager;
import com.redefocus.hub.managers.StartManager;
import com.redefocus.hub.tags.managers.TagsManager;
import com.redefocus.hub.util.Helper;
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

        playerInventory.setHelmet(null);
        playerInventory.setChestplate(null);
        playerInventory.setLeggings(null);
        playerInventory.setBoots(null);

        playerInventory.clear();

        player.teleport(SpawnManager.getSpawn());

        LobbyItem.giveItems(player);

        TagsManager tagsManager = StartManager.getTagsManager();

        tagsManager.setNametag(
                player.getName(),
                Helper.getPrefix(player.getName()),
                Helper.getSuffix(player.getName()),
                Helper.getRank(player.getName())
        );
    }
}
