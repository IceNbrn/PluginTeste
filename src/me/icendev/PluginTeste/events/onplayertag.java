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
public class onplayertag implements Listener{
    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event)
    {
        event.getFormat();
        Player p = event.getPlayer();
        PermissionUser user = PermissionsEx.getUser(p);
        if (user.inGroup("Admin")) {
            event.setFormat(ChatColor.translateAlternateColorCodes('&', "&9[Admin] ") + ChatColor.WHITE + p.getDisplayName() + " §6§l» " + ChatColor.GRAY + event.getMessage());
        }else{
            event.setFormat(ChatColor.GRAY + p.getDisplayName() + " §6§l» " + ChatColor.GRAY + event.getMessage());
        }
    }

    private String cc(String message)
    {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
