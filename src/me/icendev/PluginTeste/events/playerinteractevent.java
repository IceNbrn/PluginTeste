package me.icendev.PluginTeste.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.block.Sign;

import java.util.Random;

/**
 * Created by Henrique on 23-05-2017.
 */
public class playerinteractevent implements Listener {
    @EventHandler
    public void OnInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        if(action == action.RIGHT_CLICK_BLOCK){
            if(event.getClickedBlock() != null){
                Block block = event.getClickedBlock();
                if (block.getType() == Material.WALL_SIGN || block.getType() == Material.SIGN_POST && block != null) {
                    Sign sign = (Sign) block.getState();
                    if(sign.getLine(1) == "[Teste]"){
                        player.sendMessage("Teste");

                    }
                }
            }
        }
    }

}
