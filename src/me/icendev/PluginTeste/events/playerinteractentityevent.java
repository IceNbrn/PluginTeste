package me.icendev.PluginTeste.events;

import me.icendev.PluginTeste.Main;
import me.icendev.PluginTeste.database.mysql;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Created by Henrique on 23-05-2017.
 */
public class playerinteractentityevent implements Listener {
    /*Main plugin = Main.getPlugin(Main.class);
    mysql db = new mysql(plugin);*/
    @EventHandler
    public void PlayerInteractEntityEvent(PlayerInteractEntityEvent e){
        if(e.getRightClicked() != null) {
            Entity entity = e.getRightClicked();
            if (entity.getType() == EntityType.VILLAGER) {
                if (entity.getCustomName().equalsIgnoreCase("ze")) {
                    e.setCancelled(true);
                    double x = e.getPlayer().getLocation().getBlockX();
                    double y = e.getPlayer().getLocation().getBlockY();
                    double z = e.getPlayer().getLocation().getBlockZ();
                    World world = e.getPlayer().getWorld();
                    Location loc1 = new Location(world,x,y,z + 1);
                    loc1.getBlock().setType(Material.BEDROCK);
                    Location loc2 = new Location(world,x + 1,y,z);
                    loc2.getBlock().setType(Material.BEDROCK);
                    Location loc3 = new Location(world,x,y,z - 1);
                    loc3.getBlock().setType(Material.BEDROCK);
                    Location loc4 = new Location(world,x - 1,y,z);
                    loc4.getBlock().setType(Material.BEDROCK);
                    Location loc5 = new Location(world,x,y - 1,z);
                    loc5.getBlock().setType(Material.BEDROCK);
                    Location loc6 = new Location(world,x,y + 2,z);
                    loc6.getBlock().setType(Material.BEDROCK);
                    Location loc7 = new Location(world,x,y + 2,z + 1);
                    loc7.getBlock().setType(Material.BEDROCK);
                    Location loc8 = new Location(world,x + 1,y + 2,z);
                    loc8.getBlock().setType(Material.BEDROCK);
                    Location loc9 = new Location(world,x,y +2 ,z - 1);
                    loc9.getBlock().setType(Material.BEDROCK);
                    Location loc10 = new Location(world,x - 1,y + 2,z);
                    loc10.getBlock().setType(Material.BEDROCK);
                } else {
                    e.getPlayer().sendMessage("NOP");
                }
            } else {
                e.getPlayer().sendMessage("NOP2");
            }
        }
    }
}
