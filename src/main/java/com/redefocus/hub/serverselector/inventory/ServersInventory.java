package com.redefocus.hub.serverselector.inventory;

import com.redefocus.hub.servers.manager.ServerManager;
import com.redefocus.hub.util.inventory.InventoryBuilder;
import com.redefocus.hub.util.inventory.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

public class ServersInventory {
    public static InventoryView open(Player player) {
        InventoryBuilder inventoryBuilder = new InventoryBuilder("Selecione o servidor", 3)
                .setCancelled(true);

        ServerManager.getServers().forEach(server -> {
            inventoryBuilder.setItem(
                    server.getSlot(),
                    new Item(server.getIcon())
                            .onClick(event -> {
                                server.send(player);
                            })
            );
        });

        return inventoryBuilder.open(player);
    }
}
