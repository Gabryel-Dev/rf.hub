package com.redefocus.hub.commands.player;

import com.redefocus.hub.FocusHub;
import com.redefocus.hub.util.Helper;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TellCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (args.length >= 2) {
            Player target = Bukkit.getPlayer(args[0]);
            String message = Helper.toMessage(Helper.removeFirst(args));

            if (target == null) {
                player.sendMessage("§cEste usuário não está online");
                return true;
            }

            player.sendMessage(StringUtils.replaceEach(
                    FocusHub.getInstance().getConfig().getString("settings.chat.tell.to"),
                    new String[] {
                            "${prefix}",
                            "${username}",
                            "${message}"
                    },
                    new String[] {
                            Helper.getPrefix(target.getName()),
                            target.getName(),
                            message
                    }
            ));
            target.sendMessage(StringUtils.replaceEach(
                    FocusHub.getInstance().getConfig().getString("settings.chat.tell.from"),
                    new String[] {
                            "${prefix}",
                            "${username}",
                            "${message}"
                    },
                    new String[] {
                            Helper.getPrefix(player.getName()),
                            player.getName(),
                            message
                    }
            ));
            return true;
        } else {
            sender.sendMessage("§cUtilize /tell <usuário> <mensagem>.");
            return true;
        }
    }
}
