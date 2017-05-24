package me.icendev.PluginTeste.events;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 * Created by Henrique on 23-05-2017.
 */
public class chatevent implements Listener{
    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event)
    {
        event.getFormat();
        Player p = event.getPlayer();
        PermissionUser user = PermissionsEx.getUser(p);
        String seta = " §6§l» ";
        if (user.inGroup("Admin")) {
            event.setFormat(ChatColor.translateAlternateColorCodes('&', "&9[Admin] ") + ChatColor.BLUE + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("Developer")){
            event.setFormat("§3[Dev] " + ChatColor.DARK_AQUA + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("Moderator")){
            event.setFormat("§2[Mod] " + ChatColor.DARK_AQUA + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("Helper")){
            event.setFormat("§1[Helper] " + ChatColor.DARK_AQUA + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("Youtuber")){
            event.setFormat("§5[YT] " + ChatColor.DARK_AQUA + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("VipEsme")){
            event.setFormat("§7[§6MVP§a+§7] " + ChatColor.WHITE + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("VipDima")){
            event.setFormat("§7[§6MVP§7] " + ChatColor.WHITE + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("VipOuro")){
            event.setFormat("§7[§bVip§a+§7] " + ChatColor.WHITE + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("VipFerro")){
            event.setFormat("§7[§bVip§7] " + ChatColor.WHITE + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("Gold")){
            event.setFormat("§7[§6O§7] " + ChatColor.WHITE + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("Iron")){
            event.setFormat("§7[§3F§7] " + ChatColor.WHITE + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("Coal")){
            event.setFormat("§7[§eC§7] " + ChatColor.WHITE + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("Wood")){
            event.setFormat("§7[§8W§7] " + ChatColor.WHITE + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else if(user.inGroup("Dirt")){
            event.setFormat("§7[§aT§7] " + ChatColor.WHITE + p.getDisplayName() + seta + ChatColor.WHITE + event.getMessage());
        }else{
            event.setFormat(ChatColor.GRAY + p.getDisplayName() + " §6§l» " + ChatColor.GRAY + event.getMessage());
        }
    }

    private String cc(String message)
    {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
