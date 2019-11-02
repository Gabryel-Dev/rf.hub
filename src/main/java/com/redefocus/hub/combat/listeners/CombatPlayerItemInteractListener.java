package com.redefocus.hub.combat.listeners;

import com.redefocus.hub.combat.data.CombatPlayer;
import com.redefocus.hub.combat.manager.CombatPlayerManager;
import com.redefocus.hub.items.LobbyItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CombatPlayerItemInteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack itemStack = player.getItemInHand();

        if (itemStack == null) return;

        Action action = event.getAction();

        if (action.name().contains("_RIGHT")) return;

        LobbyItem lobbyItem = LobbyItem.COMBAT;
        ItemStack icon = lobbyItem.getIcon();

        LobbyItem lobbyItem1 = LobbyItem.NO_COMBAT;
        ItemStack icon1 = lobbyItem1.getIcon();

        if (icon1.isSimilar(itemStack) && CombatPlayerManager.isBattling(player)) {
            CombatPlayer combatPlayer = CombatPlayerManager.get(player.getUniqueId());

            combatPlayer.end();
            return;
        }

        if (icon.isSimilar(itemStack)) {
            CombatPlayerManager.add(player.getUniqueId());

            CombatPlayer combatPlayer = CombatPlayerManager.get(player.getUniqueId());

            combatPlayer.start();

            PlayerInventory playerInventory = player.getInventory();

            playerInventory.setItem(
                    lobbyItem1.getSlot(),
                    lobbyItem1.getIcon()
            );
        }
    }
}
