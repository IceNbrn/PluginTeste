package me.icendev.PluginTeste.events;

import me.icendev.PluginTeste.Main;
import net.minecraft.server.v1_11_R1.DataConverterMaterialId;
import net.minecraft.server.v1_11_R1.MinecraftKey;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import javax.persistence.Id;

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
        player.sendMessage("§7buy ou sell <preço>");
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
                String idItem1 = "";
                int idItem2 = 0;
                boolean idItemContem = false;
                int Quantidade = 0;
                int Preco = 0;
                if(idItem.contains(":")){
                    idItemContem = true;
                    String[] IdItem_ = idItem.split(":");
                    idItem1 = IdItem_[0];
                    idItem2 = Integer.parseInt(IdItem_[1]);
                }
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
                    e.setLine(1, "§5Buy " + ChatColor.DARK_BLUE + Preco);
                    e.setLine(2, idItem + " " + Quantidade);
                    if (isServerShop) {
                        e.setLine(3, "§7(Server)");
                    } else {
                        e.setLine(3, "§7" + e.getPlayer().getName());
                    }
                    try {
                        World world = e.getPlayer().getWorld();
                        Location loc = e.getBlock().getLocation();
                        Location loc1 = new Location(world,loc.getBlockX(),loc.getBlockY() + 1,loc.getBlockZ());
                        loc1.getBlock().setType(Material.AIR);
                        ItemFrame itemFrame = (ItemFrame)world.spawnEntity(loc1, EntityType.ITEM_FRAME);
                        itemFrame.setItem(new ItemStack(plugin.parseMat(idItem)));
                    }catch (IllegalArgumentException ev){
                        e.getPlayer().sendMessage("§cNão é possivel colocar o quadro com o item pois existe algo a obstruir");
                    }

                } else {
                    e.setLine(0, "§a§l[Shop]");
                    e.setLine(1, "§5Sell "+ ChatColor.DARK_BLUE + Preco);
                    e.setLine(2, idItem + " " + Quantidade);
                    if (isServerShop) {
                        e.setLine(3, "§7(Server)");
                    } else {
                        e.setLine(3, "§7" + e.getPlayer().getName());
                    }
                    try {
                        World world = e.getPlayer().getWorld();
                        Location loc = e.getBlock().getLocation();
                        Location loc1 = new Location(world,loc.getBlockX(),loc.getBlockY() + 1,loc.getBlockZ());
                        loc1.getBlock().setType(Material.AIR);
                        ItemFrame itemFrame = (ItemFrame)world.spawnEntity(loc1, EntityType.ITEM_FRAME);

                        if(idItemContem){
                            e.getPlayer().sendMessage(idItem1+" "+idItem2);
                            itemFrame.setItem(new ItemStack( plugin.parseMat(idItem1),1, (byte)idItem2 ));
                        }else{
                            e.getPlayer().sendMessage(idItem1+" "+idItem2+".");
                            itemFrame.setItem(new ItemStack(plugin.parseMat(idItem)));
                        }

                    }catch (NullPointerException ev){
                        e.getPlayer().sendMessage("§cO item não existe!");
                    }catch (IllegalArgumentException ev){
                        e.getPlayer().sendMessage("§cNão é possivel colocar o quadro com o item pois existe algo a obstruir");
                    }

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
