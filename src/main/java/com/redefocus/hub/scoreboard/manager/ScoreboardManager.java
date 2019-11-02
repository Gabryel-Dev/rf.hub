package com.redefocus.hub.scoreboard.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.redefocus.hub.FocusHub;
import com.redefocus.hub.scoreboard.util.Scoreboard;
import com.redefocus.hub.servers.data.Server;
import com.redefocus.hub.servers.manager.ServerManager;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

public class ScoreboardManager {
    private static HashMap<UUID, Scoreboard> scoreboards = Maps.newHashMap();
    private static Queue<Player> players = Lists.newLinkedList();

    public static Scoreboard createScoreboard(Player player) {
        ConfigurationSection configurationSection = FocusHub.getInstance().getConfig().getConfigurationSection("settings.scoreboard");

        String displayName = configurationSection.getString("display_name");

        Scoreboard scoreboard = new Scoreboard(player, displayName);

        scoreboard.create();

        ScoreboardManager.setLines(scoreboard);

        ScoreboardManager.scoreboards.put(
                player.getUniqueId(),
                scoreboard
        );

        return scoreboard;
    }

    public static void updateScoreboard(Player player) {
        Scoreboard scoreboard = ScoreboardManager.scoreboards.getOrDefault(
                player.getUniqueId(),
                ScoreboardManager.createScoreboard(player)
        );

        ScoreboardManager.setLines(scoreboard);
    }

    private static void setLines(Scoreboard scoreboard) {
        ConfigurationSection configurationSection = FocusHub.getInstance().getConfig().getConfigurationSection("settings.scoreboard");

        List<String> lines = configurationSection.getStringList("lines");

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.contains("::")) {
                String[] parts = line.split("\\$\\{");

                String serverName = line.split("\\$\\{")[1].split("}")[0].split("::")[0];

                Server server = ServerManager.getServer(serverName);

                if (server != null) {
                    line = StringUtils.replaceEach(
                            line,
                            new String[]{
                                    "${" + serverName + "::online}"
                            },
                            new String[]{
                                    server.getPlayerCount().toString()
                            }
                    );
                }
            }

            scoreboard.setLine(
                    i,
                    line
            );
        }

        String footer = configurationSection.getString("footer");

        scoreboard.setLine(14, footer);
    }

    public static void refresh() {
        if (ScoreboardManager.players.isEmpty()) players.addAll(Bukkit.getOnlinePlayers());

        Player player = ScoreboardManager.players.poll();

        if (player != null && player.isOnline()) ScoreboardManager.updateScoreboard(player);
    }
}
