package me.icendev.PluginTeste.database;

import me.icendev.PluginTeste.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Henrique on 22-05-2017.
 */
public class mysql implements Listener{
    Main plugin;

    public mysql(Main main){
        this.plugin = main;
        //plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    public void insertTeste(String uuid, String username, int coins){
        PreparedStatement insert = null;
        try {
            insert = plugin.getConnection().prepareStatement("INSERT INTO players (UUID,username,coins) VALUES(?,?,?)");
            insert.setString(1,uuid);
            insert.setString(2,username);
            insert.setInt(3,coins);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void createPlayer(UUID uniqueId, Player player) {
        try{
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM players WHERE UUID = ?");
            statement.setString(1,uniqueId.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            System.out.print(1);
            if(!playerExists(uniqueId)){
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO players (UUID,username,coins) VALUES(?,?,?)");
                insert.setString(1,uniqueId.toString());
                insert.setString(2,player.getName());
                insert.setInt(3,500);
                insert.executeUpdate();

                plugin.getServer(). broadcastMessage(plugin.pluginName + ChatColor.GREEN + "Utilizador inserido na base de dados!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean playerExists(UUID uuid) {
        try {
            PreparedStatement statement = plugin.getConnection()
                    .prepareStatement("SELECT * FROM players WHERE UUID=?");
            statement.setString(1, uuid.toString());

            ResultSet results = statement.executeQuery();
            if (results.next()) {
                plugin.getServer().broadcastMessage(ChatColor.YELLOW + "Player Found");
                return true;
            }
            plugin.getServer().broadcastMessage(ChatColor.RED + "Player NOT Found");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void updateCoins(UUID uuid,int coins) {
        try {
            PreparedStatement statement = plugin.getConnection()
                    .prepareStatement("UPDATE players SET coins = ? WHERE UUID=?");
            statement.setInt(1, coins);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void darCoins(UUID from,UUID to,String coins){
        try {
            PreparedStatement statement = plugin.getConnection()
                    .prepareStatement("UPDATE players SET coins = coins + ? WHERE UUID=?");
            statement.setInt(1, Integer.parseInt(coins));
            statement.setString(2, to.toString());
            statement.executeUpdate();
            PreparedStatement statement2 = plugin.getConnection()
                    .prepareStatement("UPDATE players SET coins = coins - ? WHERE UUID=?");
            statement2.setInt(1, Integer.parseInt(coins));
            statement2.setString(2, from.toString());
            statement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Map<Integer, String> getTop10Coins(){
        Map<Integer, String> out = new TreeMap<Integer, String>(Collections.reverseOrder());
        try {
            PreparedStatement statement = plugin.getConnection()
                    .prepareStatement("SELECT * FROM players ORDER BY coins DESC LIMIT 0,10");
            ResultSet results = statement.executeQuery();
            while (results.next()){
                out.put(results.getInt("coins"),results.getString("username"));
            }
            return out;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }
    public int getCoins(UUID uuid) {
        try {
            PreparedStatement statement = plugin.getConnection()
                    .prepareStatement("SELECT * FROM players WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();

            return results.getInt("COINS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
