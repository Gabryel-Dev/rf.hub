package com.redefocus.hub.combat.manager;

import com.google.common.collect.Maps;
import com.redefocus.hub.combat.data.CombatPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatPlayerManager {
    private static HashMap<UUID, CombatPlayer> battling = Maps.newHashMap();

    public static CombatPlayer remove(UUID uuid) {
        return CombatPlayerManager.battling.remove(uuid);
    }

    public static CombatPlayer add(UUID uuid) {
        return CombatPlayerManager.battling.put(uuid, new CombatPlayer());
    }

    public static CombatPlayer get(UUID uuid) {
        return CombatPlayerManager.battling.get(uuid);
    }

    public static Boolean isBattling(Player player) {
        return CombatPlayerManager.battling.containsKey(player.getUniqueId());
    }

    public static Player getPlayer(CombatPlayer combatPlayer) {
        for (Map.Entry<UUID, CombatPlayer> entry : CombatPlayerManager.battling.entrySet()) {
            UUID uuid = entry.getKey();
            CombatPlayer combatPlayer1 = entry.getValue();

            Player player = Bukkit.getPlayer(uuid);

            if (combatPlayer.equals(combatPlayer1)) return player;
        }

        return null;
    }
}
