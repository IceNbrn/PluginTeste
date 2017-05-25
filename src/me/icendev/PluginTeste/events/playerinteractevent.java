package me.icendev.PluginTeste.events;

import me.icendev.PluginTeste.Main;
import net.minecraft.server.v1_11_R1.Item;
import net.minecraft.server.v1_11_R1.MinecraftKey;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.block.Sign;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Random;

/**
 * Created by Henrique on 23-05-2017.
 */
public class playerinteractevent implements Listener {
    Main plugin = Main.getPlugin(Main.class);
    public int removeInventoryItems(PlayerInventory inv, ItemStack item, int amount) {
        int Amount = amount;
        for (ItemStack is : inv.getContents()) {
            if (is != null && is.getType() == item.getType()) {
                int newamount = is.getAmount() - amount;
                System.out.print(is.getAmount());
                System.out.print(amount);
                if (newamount > 0) {
                    System.out.print(newamount);
                    is.setAmount(newamount);
                    break;
                } else {
                    inv.remove(is);
                    amount = -newamount;
                    /*if (amount == 0){
                        System.out.print("IF: " + amount);
                        break;
                    }*/
                }
            }
        }
        return Amount-amount;
    }
    @EventHandler
    public void onPlayerInteract1(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();
            if(b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST) {
                Sign sign = (Sign) b.getState();
                String[] lines = sign.getLines();
                String[] line1 = lines[1].split(" ");
                String texto = line1[0];
                String preco = line1[1];
                String[] line2 = lines[2].split(" ");

                String idItem = line2[0];
                String quantidade = line2[1];
                String idItem1 = "";
                int idItem2 = 0;
                boolean idItemContem = false;
                if(lines[0].equalsIgnoreCase("§a§l[Shop]")) {
                    try {
                        int Quantidade = Integer.parseInt(quantidade);
                        if(idItem.contains(":")){
                            idItemContem = true;
                            String[] IdItem_ = idItem.split(":");
                            idItem1 = IdItem_[0];
                            idItem2 = Integer.parseInt(IdItem_[1]);
                            e.getPlayer().sendMessage(idItem1+" " +idItem2);
                        }else{
                            idItemContem = false;
                        }
                        if(texto.equalsIgnoreCase("§5Buy")){
                            if(idItemContem){
                                e.getPlayer().getInventory().addItem(new ItemStack(plugin.parseMat(idItem1),Integer.parseInt(quantidade),(byte)idItem2));
                            }else{
                                e.getPlayer().getInventory().addItem(new ItemStack(plugin.parseMat(idItem),Integer.parseInt(quantidade)));
                            }

                            e.getPlayer().sendMessage("§aCompra");
                        }else if(texto.equalsIgnoreCase("§5Sell")){
                            int nVendidos = 0;
                            if(idItemContem) {
                                nVendidos = removeInventoryItems(e.getPlayer().getInventory(),new ItemStack(plugin.parseMat(idItem1),(byte)idItem2),Quantidade);
                                //e.getPlayer().sendMessage("" +;
                                //e.getPlayer().getInventory().removeItem(new ItemStack(plugin.parseMat(idItem1),Integer.parseInt(quantidade),(byte)idItem2));
                            }else{
                                nVendidos = removeInventoryItems(e.getPlayer().getInventory(),new ItemStack(plugin.parseMat(idItem)),Quantidade);
                                //e.getPlayer().getInventory().removeItem(new ItemStack(plugin.parseMat(idItem),Integer.parseInt(quantidade));
                            }

                            e.getPlayer().sendMessage("§aVendeu " + nVendidos + " itens");
                        }
                        System.out.print(texto);


                    }catch(NumberFormatException ev){
                        e.getPlayer().sendMessage("§cQuantidade inválida!");
                        e.setCancelled(true);
                    }

                }
            }
        }
    }

}
