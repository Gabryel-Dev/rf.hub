package com.redefocus.hub.items;

import com.redefocus.hub.util.inventory.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
@Getter
public enum LobbyItem {
    COMBAT(
            new Item(Material.DIAMOND_SWORD)
                    .hideAttributes()
                    .name("§bEntrar no modo batalha")
                    .build(),
            7
    ),
    SERVERS(
            new Item(Material.COMPASS)
                    .name("§bSelecionar servidor")
                    .build(),
            4
    ),
    SHOP(
            new Item(Material.SKULL_ITEM)
                    .data(3)
                    .name("§bAcessar loja")
                    .build(),
            1
    );

    private final ItemStack icon;
    private final Integer slot;

    public static LobbyItem getLobbyItem(ItemStack itemStack) {
        for (LobbyItem lobbyItem : LobbyItem.values()) {
            if (lobbyItem.getIcon().isSimilar(itemStack)) return lobbyItem;
        }
        return null;
    }
}
