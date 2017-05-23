package me.icendev.PluginTeste.commands;

import me.icendev.PluginTeste.Main;
import me.icendev.PluginTeste.database.mysql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Henrique on 23-05-2017.
 */
public class chat implements CommandExecutor {
    Main plugin = Main.getPlugin(Main.class);
    mysql db = new mysql(plugin);
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        String prefixo = ChatColor.DARK_RED + "[StaffChat] ";
        if(args.length >= 1){
            if (sender instanceof Player) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission("mostwanted.staffchat")) {
                        player.sendMessage("");
                        player.sendMessage(prefixo + ChatColor.DARK_GRAY +"(" + sender.getName() + ") §6§l» " + ChatColor.WHITE + args[0]);
                        player.sendMessage("");
                        return true;
                    }
                }
            }else{
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission("mostwanted.staffchat")) {
                        player.sendMessage("");
                        player.sendMessage(prefixo + ChatColor.DARK_GRAY +"(" + sender.getName() + ") §6§l» " + ChatColor.WHITE + args[0]);
                        player.sendMessage("");
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
}
