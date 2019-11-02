package com.redefocus.hub.items;

import com.redefocus.hub.util.inventory.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

@RequiredArgsConstructor
@Getter
public enum LobbyItem {
    COMBAT(
            new Item(Material.DIAMOND_SWORD)
                    .hideAttributes()
                    .name("§bEntrar no modo batalha")
                    .build(),
            7,
            true
    ),
    NO_COMBAT(
            new Item(Material.BARRIER)
                    .hideAttributes()
                    .name("§cSair do modo batalha")
                    .build(),
            8,
            false
    ),
    SERVERS(
            new Item(Material.COMPASS)
                    .name("§bSelecionar servidor")
                    .build(),
            4,
            true
    ),
    SHOP(
            new Item(Material.SKULL_ITEM)
                    .data(3)
                    .name("§bAcessar loja")
                    .build(),
            1,
            true
    );

    private final ItemStack icon;
    private final Integer slot;
    private final Boolean playable;

    public static void giveItems(Player player) {
        PlayerInventory playerInventory = player.getInventory();

        for (LobbyItem lobbyItem : LobbyItem.values()) {
            if (!lobbyItem.isPlayable()) continue;

            ItemStack icon = lobbyItem.getIcon().clone();

            Item item = new Item(icon);

            if (lobbyItem == LobbyItem.SHOP) item.owner(player.getName());

            playerInventory.setItem(
                    lobbyItem.getSlot(),
                    item.build()
            );
        }
    }

    public static LobbyItem getLobbyItem(ItemStack itemStack) {
        if (itemStack.getType() == Material.SKULL_ITEM) return LobbyItem.SHOP;

        for (LobbyItem lobbyItem : LobbyItem.values()) {
            if (lobbyItem.getIcon().isSimilar(itemStack)) return lobbyItem;
        }
        return null;
    }

    public Boolean isPlayable() {
        return this.playable;
    }
}
