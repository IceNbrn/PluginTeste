package me.icendev.PluginTeste.commands;

import me.icendev.PluginTeste.Main;
import me.icendev.PluginTeste.database.mysql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by Henrique on 22-05-2017.
 */
public class coins implements CommandExecutor {
    Main plugin = Main.getPlugin(Main.class);
    mysql db = new mysql(plugin);
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(plugin.pluginName + ChatColor.RED + "Este comando só pode ser usado por jogadores!");
            return false;
        }
        if(args.length == 0){
            try {
                sender.sendMessage(ChatColor.GREEN + "Coins: " + db.getCoins(((Player) sender).getUniqueId()));
                return true;
            }catch (ClassCastException e){
                e.printStackTrace();
                return false;
            }
        }
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("top")){
                //Map<Integer,String> treeMap = new TreeMap<Integer,String>(Collections.reverseOrder());
                Set set = db.getTop10Coins().entrySet();
                Iterator iterator = set.iterator();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"                 &6&l{Top 10 Ricos}"));
                sender.sendMessage(" ");
                sender.sendMessage(" ");
                while (iterator.hasNext()){
                    Map.Entry mentry = (Map.Entry)iterator.next();
                    sender.sendMessage("    "+ChatColor.AQUA + mentry.getValue().toString() + ChatColor.BLUE + " (" + mentry.getKey() + " coins)");
                }
                sender.sendMessage(" ");
                sender.sendMessage(" ");
                return true;
            }
            if(args[0].equalsIgnoreCase("help")){
                plugin.comandosCoins(sender);
                return true;
            }
            return false;
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("ver")){
                Player player;
                player = Bukkit.getPlayer(args[1]);
                if(player == null){
                    sender.sendMessage(ChatColor.RED + "Jogador não encontrado");
                    return false;
                }
                sender.sendMessage(ChatColor.GREEN + "Coins de " + player.getName() + ": " + db.getCoins(player.getUniqueId()));
                return true;
            }else{
                plugin.comandosCoins(sender);
                return false;
            }
        }
        if(args.length == 3){
            if(args[0].equalsIgnoreCase("dar")){
                UUID senderP = ((Player) sender).getUniqueId();
                Player player;
                player = Bukkit.getPlayer(args[1]);
                if(player == null){
                    sender.sendMessage(ChatColor.RED + "Jogador não encontrado");
                    return false;
                }
                if(args[2] != null){
                    try {
                        if(Integer.parseInt(args[2]) > 0){
                            db.darCoins(senderP,player.getUniqueId(),args[2]);
                            sender.sendMessage(ChatColor.GREEN + "----------[TRANSAÇÃO]----------");
                            sender.sendMessage(ChatColor.GREEN + "VALOR DA TRANSAÇÃO: " + ChatColor.GRAY + args[2]);
                            sender.sendMessage(ChatColor.GREEN + "TRANSAÇÃO ENVIADA A: " + ChatColor.AQUA + player.getName());
                            sender.sendMessage(ChatColor.GREEN + "SALDO ATUAL: " + ChatColor.GRAY + db.getCoins(senderP));
                            player.sendMessage(ChatColor.GREEN + "TRANSAÇÃO");
                            player.sendMessage(ChatColor.GREEN + "Recebes-te " + args[2] + " de " + sender.getName());
                            return true;
                        }
                    }catch (NumberFormatException e){
                        sender.sendMessage("Número inválido!");
                    }
                }else{
                    sender.sendMessage(ChatColor.RED + "Tenta usar /coins dar <nome do jogador> <quantia>");
                    return false;
                }
            }
            sender.sendMessage(ChatColor.RED + "Tenta usar /coins dar <nome do jogador> <quantia>");
            return false;
        }
        plugin.comandosCoins(sender);
        return false;
    }
}
