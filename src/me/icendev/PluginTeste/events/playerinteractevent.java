package me.icendev.PluginTeste.events;

import me.icendev.PluginTeste.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.block.Sign;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * Created by Henrique on 23-05-2017.
 */
public class playerinteractevent implements Listener {
    Main plugin = Main.getPlugin(Main.class);
    @EventHandler
    public void onPlayerInteract1(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();
            if(b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST) {
                Sign sign = (Sign) b.getState();
                String[] lines = sign.getLines();
                if(lines[0].equalsIgnoreCase("§a§l[Shop]")) {
                    String[] line1 = lines[1].split(" ");
                    String texto = line1[0];
                    String preco = line1[1];
                    String[] line2 = lines[2].split(" ");
                    String idItem = line2[0];
                    String quantidade = line2[1];
                    try {
                        if(texto == "§5Buy"){
                            e.getPlayer().getInventory().addItem(new ItemStack(plugin.parseMat(idItem),Integer.parseInt(quantidade)));
                            e.getPlayer().sendMessage("Compra");
                        }else if(texto == "§5Sell"){
                            e.getPlayer().getInventory().removeItem(new ItemStack(plugin.parseMat(idItem),Integer.parseInt(quantidade)));
                            e.getPlayer().sendMessage("Venda");
                        }

                    }catch(NumberFormatException ev){
                        e.getPlayer().sendMessage("§cQuantidade inválida!");
                        e.setCancelled(true);
                    }

                }
            }
        }
    }

}
