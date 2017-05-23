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
public class sc implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        String prefix = ChatColor.DARK_RED + "[StaffChat] ";

        if (args.length > 0)
        {
            String msg = "";
            int x = 1;
            String[] arrayOfString;
            int j = (arrayOfString = args).length;
            String a;
            for (int i = 0; i < j; i++)
            {
                a = arrayOfString[i];
                if (x == 0) {
                    x++;
                } else {
                    msg = msg + " " + a;
                }
            }
            msg = msg.trim();
            if ((sender instanceof Player)) {

                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (p.hasPermission("mostwanted.staff") || p.isOp()) {
                        p.sendMessage("");
                        p.sendMessage(prefix + ChatColor.WHITE + sender.getName() + ChatColor.AQUA + " §6§l» " + ChatColor.YELLOW + ChatColor.translateAlternateColorCodes('&', msg));
                        p.sendMessage("");
                    }
                }
            }
            if (!(sender instanceof Player)) {
                for (Player p : Bukkit.getServer().getOnlinePlayers() ) {
                    if (p.hasPermission("mostwanted.staff") || p.isOp()) {
                        p.sendMessage(prefix + sender.getName() + " §6§l» " + ChatColor.YELLOW + ChatColor.translateAlternateColorCodes('&', msg));
                    }
                }
            }
        }else{
            sender.sendMessage(prefix + "Use: /sc <msg>");
            return false;
        }
        return false;
    }
}
