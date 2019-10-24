package com.redefocus.hub.combat.listeners;

import com.redefocus.hub.combat.data.CombatPlayer;
import com.redefocus.hub.combat.manager.CombatPlayerManager;
import com.redefocus.hub.items.LobbyItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CombatPlayerItemInteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack itemStack = player.getItemInHand();

        if (itemStack == null) return;

        LobbyItem lobbyItem = LobbyItem.COMBAT;
        ItemStack icon = lobbyItem.getIcon();

        if (icon.isSimilar(itemStack)) {
            CombatPlayer combatPlayer = CombatPlayerManager.add(player.getUniqueId());

            combatPlayer.start();
        }
    }
}
