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

                } else {
                    e.getPlayer().sendMessage("NOP");
                }
            } else {
                e.getPlayer().sendMessage("NOP2");
            }
        }
    }
}
