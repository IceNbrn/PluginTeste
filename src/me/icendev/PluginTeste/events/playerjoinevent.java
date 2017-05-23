package me.icendev.PluginTeste.events;

import me.icendev.PluginTeste.Main;
import me.icendev.PluginTeste.database.mysql;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Henrique on 22-05-2017.
 */
public class playerjoinevent implements Listener{
    Main plugin = Main.getPlugin(Main.class);
    mysql db = new mysql(plugin);
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        db.createPlayer(player.getUniqueId(), player);
        e.setJoinMessage(null);
        if (player.hasPermission("mostwanted.admin"))
        {
            player.setPlayerListName(ChatColor.BLUE + player.getName());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&9&lADMIN &7" + player.getName()) + ChatColor.translateAlternateColorCodes('&', " &aentrou no servidor."));
        }
        else if (player.hasPermission("craftbale.developer"))
        {
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&a&lDEV &7") + player.getName());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&a&lDEV &7") + player.getName() + ChatColor.translateAlternateColorCodes('&', " &9entrou no servidor."));
        }
        else if (player.hasPermission("craftbale.builder"))
        {
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&5&lBLD &7") + player.getName());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&5&lBUILDER &7") + player.getName() + ChatColor.translateAlternateColorCodes('&', " &9entrou no servidor."));
        }
        else if (player.hasPermission("craftbale.ajudante"))
        {
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&9&lAJD &7") + player.getName());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&e&lAJUDANTE &7") + player.getName() + ChatColor.translateAlternateColorCodes('&', " &9entrou no servidor."));
        }
        else if (player.hasPermission("craftbale.youtuber"))
        {
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&8&lY&4&lTR &7") + player.getName());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&8&lYOU&4&lTUBER &7") + player.getName() + ChatColor.translateAlternateColorCodes('&', " &9entrou no servidor."));
        }
        else if (player.hasPermission("craftbale.vipbale"))
        {
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&b&lBALE &7") + player.getName());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&b&lBALE &7") + player.getName() + ChatColor.translateAlternateColorCodes('&', " &9entrou no servidor."));
        }
        else if (player.hasPermission("craftbale.vip+"))
        {
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&3&lVIP&b&l+ &7") + player.getName());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&3&lVIP&b&l+ &7") + player.getName() + ChatColor.translateAlternateColorCodes('&', " &9entrou no servidor."));
        }
        else if (player.hasPermission("craftbale.vip"))
        {
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&3&lVIP &7") + player.getName());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&3&lVIP &7") + player.getName() + ChatColor.translateAlternateColorCodes('&', " &9entrou no servidor."));
        }
        else
        {
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&7") + player.getName());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&8") + player.getName() + ChatColor.translateAlternateColorCodes('&', " &9entrou no servidor."));
        }
        if (!player.hasPlayedBefore())
        {
            player.sendMessage("");
            player.sendMessage("");
            player.sendMessage(ChatColor.DARK_AQUA + "Seja Bem-vindo ao MostWanted!");
            player.sendMessage("");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&1&lO Nosso Servidor Tem:"));
            player.sendMessage(ChatColor.AQUA + "    >" + ChatColor.GREEN + "  Missões");
            player.sendMessage("");
            player.sendMessage("");
        }
        else
        {
            player.sendMessage("");
            player.sendMessage("");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lBem-vindo de volta, ") + player.getName() + "!");
            player.sendMessage("");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cA staff deseja-te um ótimo jogo aqui no nosso server!"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNão te esqueças de divulgar o server se gostas-te :P."));
            player.sendMessage("");
            player.sendMessage("");
        }
    }
}

