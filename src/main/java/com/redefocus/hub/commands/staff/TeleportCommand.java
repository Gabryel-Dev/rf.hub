package com.redefocus.hub.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.hasPermission("redefocus.commands.teleport") && sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                String username = args[0];
                Player target = Bukkit.getPlayer(username);

                if (target == null) {
                    player.sendMessage("§cEste usuário não está online.");
                    return true;
                }

                player.teleport(target);
                player.sendMessage("§eTeletransportando-se para " + target.getName());
                return true;
            } else {
                sender.sendMessage("§cUtilize /teleport <usuário>.");
                return true;
            }
        } else {
            sender.sendMessage("§cVocê não possui permissão para executar este comando");
            return true;
        }
    }
}
