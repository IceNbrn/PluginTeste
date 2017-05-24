package me.icendev.PluginTeste.commands;

import me.icendev.PluginTeste.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Henrique on 24-05-2017.
 */
public class cage implements CommandExecutor{

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Main plugin = Main.getPlugin(Main.class);
        if (args.length == 1)
        {
            if(!(sender instanceof Player)){
                sender.sendMessage(plugin.pluginName + ChatColor.RED + "Este comando só pode ser usado por jogadores!");
                return false;
            }
            Player player;
            player = Bukkit.getPlayer(args[0]);
            if(player == null){
                sender.sendMessage("§cO jogador não existe ou está offline!");
                return false;
            }

            double x = player.getLocation().getBlockX();
            double y = player.getPlayer().getLocation().getBlockY();
            double z = player.getPlayer().getLocation().getBlockZ();
            World world = player.getPlayer().getWorld();
            Location loc1 = new Location(world,x,y,z + 1);
            Location loc2 = new Location(world,x + 1,y,z);
            Location loc3 = new Location(world,x,y,z - 1);
            Location loc4 = new Location(world,x - 1,y,z);
            Location loc5 = new Location(world,x,y - 1,z);
            Location loc6 = new Location(world,x,y + 3,z);
            Location loc7 = new Location(world,x,y + 2,z + 1);
            Location loc8 = new Location(world,x + 1,y + 2,z);
            Location loc9 = new Location(world,x,y +2 ,z - 1);
            Location loc10 = new Location(world,x - 1,y + 2,z);

            loc1.getBlock().setType(Material.BEDROCK);
            loc2.getBlock().setType(Material.BEDROCK);
            loc3.getBlock().setType(Material.BEDROCK);
            loc4.getBlock().setType(Material.BEDROCK);
            loc5.getBlock().setType(Material.BEDROCK);
            loc6.getBlock().setType(Material.BEDROCK);
            loc7.getBlock().setType(Material.BEDROCK);
            loc8.getBlock().setType(Material.BEDROCK);
            loc9.getBlock().setType(Material.BEDROCK);
            loc10.getBlock().setType(Material.BEDROCK);
            if(player.isFlying()){
                player.setFlying(false);
            }
            player.sendMessage("");
            player.sendMessage("§cFoste colocado na jaula por: §c§l" + sender.getName());
            player.sendMessage("");
            sender.sendMessage("§aColocas-te o jogador §a§l" + args[0] + "§a na jaula");
        }else{
            sender.sendMessage(plugin.pluginName + "§c Tenta usar: /cage <jogador>");
            return false;
        }
        return false;
    }
}
