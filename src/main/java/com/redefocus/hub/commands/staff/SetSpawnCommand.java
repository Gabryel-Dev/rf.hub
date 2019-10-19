package com.redefocus.hub.commands.staff;

import com.redefocus.hub.FocusHub;
import com.redefocus.hub.managers.SpawnManager;
import com.redefocus.hub.util.LocationSerialize;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] label) {
        if (sender.hasPermission("redefocus.commands.setspawn") && sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();

            String serializedLocation = LocationSerialize.toString(location);

            FocusHub.getInstance().getConfig().set("settings.spawn", serializedLocation);
            SpawnManager.setSpawn(location);

            player.sendMessage("§eSpawn atualizado com sucesso.");
            return true;
        } else {
            sender.sendMessage("§cVocê não possui permissão para executar este comando");
            return true;
        }
    }
}
