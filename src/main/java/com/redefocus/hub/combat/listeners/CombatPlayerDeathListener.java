package com.redefocus.hub.combat.listeners;

import com.redefocus.hub.combat.manager.CombatPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class CombatPlayerDeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();

        event.getDrops().clear();
        event.setDroppedExp(0);

        if (killer == null) return;

        CombatPlayerManager.getPlayers().forEach(player1 -> player1.sendMessage("§3" + player.getName() + " §cfoi morto por §3" + killer.getName()));
    }
}
