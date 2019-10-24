package com.redefocus.hub.listeners.general;

import com.redefocus.hub.FocusHub;
import com.redefocus.hub.items.LobbyItem;
import com.redefocus.hub.serverselector.inventory.ServersInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerLobbyItemInteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack itemStack = player.getItemInHand();

        if (itemStack != null) {
            LobbyItem lobbyItem = LobbyItem.getLobbyItem(itemStack);

            if (lobbyItem == null ) return;

            String url = FocusHub.getInstance().getConfig().getString("settings.shop_url");

            if (lobbyItem == LobbyItem.SHOP) player.sendMessage("§eAcesse nossa loja em: §l" + url);

            if (lobbyItem == LobbyItem.SERVERS) ServersInventory.open(player);
        }
    }
}
