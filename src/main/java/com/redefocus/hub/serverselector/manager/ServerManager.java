package com.redefocus.hub.serverselector.manager;

import com.google.common.collect.Lists;
import com.redefocus.hub.FocusHub;
import com.redefocus.hub.serverselector.data.Server;
import com.redefocus.hub.util.inventory.Item;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class ServerManager {
    private static List<Server> servers = Lists.newArrayList();

    public ServerManager() {
        ConfigurationSection configurationSection = FocusHub.getInstance().getConfig().getConfigurationSection("servers");

        configurationSection.getKeys(true).forEach(key -> {
            Integer slot = configurationSection.getInt(key + ".slot");
            Integer id = configurationSection.getInt(key + ".id");
            Integer data = configurationSection.getInt(key + ".data");
            String displayName = configurationSection.getString(key + ".display_name");
            List<String> lore = configurationSection.getStringList(key + ".lore");

            Server server = new Server(
                    key,
                    new Item(Material.getMaterial(id))
                            .data(data)
                            .name(displayName)
                            .lore(lore)
                            .build(),
                    slot
            );

            ServerManager.servers.add(server);
        });
    }

    public static List<Server> getServers() {
        return ServerManager.servers;
    }
}
