package me.icendev.PluginTeste.events;

import me.icendev.PluginTeste.Main;
import net.minecraft.server.v1_11_R1.DataConverterMaterialId;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Henrique on 24-05-2017.
 */
public class color_chat_sign implements Listener {
    //shop               0
    //buy/sell <preco>   1
    //item <quantidade>  2
    //server             3
    Main plugin = Main.getPlugin(Main.class);
    public void exemploPlaca(Player player){
        player.sendMessage("    §5§lExemplo da loja");
        player.sendMessage("§7shop");
        player.sendMessage("§7buy ou seel <preço>");
        player.sendMessage("§7id item <quantidade>");
        if(player.hasPermission("mostwanted.loja.admin")){
            player.sendMessage("§7server (loja do servidor)");
        }
    }
    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        Player p = e.getPlayer();
        boolean isServerShop = true;
        if (e.getLine(0).equalsIgnoreCase("shop")) {
            int nSpaces = e.getLine(1).length() - e.getLine(1).replaceAll(" ", "").length();
            int nSpaces2 = e.getLine(2).length() - e.getLine(2).replaceAll(" ", "").length();
            if (e.getLine(1).isEmpty() || nSpaces > 1) {
                exemploPlaca(e.getPlayer());
                e.setCancelled(true);
            } else if (e.getLine(2).isEmpty() || nSpaces2 > 1) {
                exemploPlaca(e.getPlayer());
                e.setCancelled(true);
            } else {
                String[] line1 = e.getLine(1).split(" ");
                String texto = line1[0];
                String preco = line1[1];
                String[] line2 = e.getLine(2).split(" ");
                String idItem = line2[0];
                String quantidade = line2[1];
                int Quantidade = 0;
                int Preco = 0;
                try {
                    Quantidade = Integer.parseInt(quantidade);
                    Preco = Integer.parseInt(preco);
                }catch (NumberFormatException e_){
                    e.getPlayer().sendMessage("§cO Número é inválido!");
                    e.setCancelled(true);
                }

                boolean buy = true;
                if (texto.equalsIgnoreCase("buy")) {
                    if (e.getLine(3).isEmpty()) {
                        isServerShop = false;
                    } else {
                        if(e.getPlayer().hasPermission("mostwanted.shop.admin")) {
                            if (e.getLine(3).equalsIgnoreCase("server"))
                                isServerShop = true;
                            else
                                isServerShop = false;
                        }else {
                            isServerShop = false;
                        }
                    }
                    buy = true;
                } else if (texto.equalsIgnoreCase("sell")) {
                    if (e.getLine(3).isEmpty()) {
                        isServerShop = false;
                    } else {
                        if(e.getPlayer().hasPermission("mostwanted.shop.admin")) {
                            if (e.getLine(3).equalsIgnoreCase("server"))
                                isServerShop = true;
                            else
                                isServerShop = false;
                        }else {
                            isServerShop = false;
                        }
                    }
                    buy = false;
                }

                if (buy) {
                    e.setLine(0, "§a§l[Shop]");
                    e.setLine(1, "§5Buy§1 " + Preco);
                    e.setLine(2, idItem + " " + Quantidade);
                    if (isServerShop) {
                        e.setLine(3, "§7(Server)");
                    } else {
                        e.setLine(3, "§7(" + e.getPlayer().getName() + ")");
                    }
                    World world = e.getPlayer().getWorld();
                    Location loc = e.getBlock().getLocation();
                    ItemFrame itemFrame = (ItemFrame)world.spawnEntity(new Location(world,loc.getBlockX(),loc.getBlockY() + 1,loc.getBlockZ()), EntityType.ITEM_FRAME);
                    itemFrame.setItem(new ItemStack(plugin.parseMat(idItem)));
                } else {
                    e.setLine(0, "§a§l[Shop]");
                    e.setLine(1, "§5Sell§1 " + Preco);
                    e.setLine(2, idItem + " " + Quantidade);
                    if (isServerShop) {
                        e.setLine(3, "§7(Server)");
                    } else {
                        e.setLine(3, "§7(" + e.getPlayer().getName() + ")");
                    }
                    World world = e.getPlayer().getWorld();
                    Location loc = e.getBlock().getLocation();
                    ItemFrame itemFrame = (ItemFrame)world.spawnEntity(new Location(world,loc.getBlockX(),loc.getBlockY() + 1,loc.getBlockZ()), EntityType.ITEM_FRAME);
                    itemFrame.setItem(new ItemStack(plugin.parseMat(idItem)));
                }

                p.sendMessage(ChatColor.GREEN + "A loja foi criada!");
            }
        }
    }
    @EventHandler
    public void onSign(SignChangeEvent e)
    {
        Player p = e.getPlayer();
        if (p.hasPermission("mostwanted.signs"))
        {
            e.setLine(0, cc(e.getLine(0)));
            e.setLine(1, cc(e.getLine(1)));
            e.setLine(2, cc(e.getLine(2)));
            e.setLine(3, cc(e.getLine(3)));
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();
        if (p.hasPermission("mostwanted.chat")) {
            e.setMessage(cc(e.getMessage()));
        }
    }

    private String cc(String message)
    {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
