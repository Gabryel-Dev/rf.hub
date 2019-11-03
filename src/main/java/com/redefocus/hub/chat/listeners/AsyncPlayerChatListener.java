package com.redefocus.hub.chat.listeners;

import com.redefocus.hub.FocusHub;
import com.redefocus.hub.chat.managers.ChatManager;
import com.redefocus.hub.util.Helper;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        Player player = event.getPlayer();
        String message = event.getMessage();

        ConfigurationSection section = FocusHub.getInstance().getConfig().getConfigurationSection("settings.chat");

        String permission = section.getString("permission");
        String bypass_permission = section.getString("bypass_permission");
        String format = section.getString("format");
        Integer cooldown = section.getInt("cooldown");

        if (cooldown != 0 && !player.hasPermission(bypass_permission) && ChatManager.hasCooldown(player.getUniqueId())) {
            player.sendMessage("§cAguarde " + ChatManager.getFormatted(player.getUniqueId()) + " para falar no chat novamente.");
            return;
        }

        if (!player.hasPermission(permission)) return;

        String message1 = StringUtils.replaceEach(
                format,
                new String[]{
                        "${prefix}",
                        "${username}",
                        "${message}"
                },
                new String[]{
                        Helper.getPrefix(player.getName()),
                        player.getName(),
                        message
                }
        );

        Bukkit.getOnlinePlayers().forEach(player1 -> player1.sendMessage(message1));

        if (!player.hasPermission(bypass_permission)) ChatManager.setCooldown(player.getUniqueId(), cooldown);
    }
}
