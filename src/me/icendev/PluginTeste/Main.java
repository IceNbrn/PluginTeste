package me.icendev.PluginTeste;

import me.icendev.PluginTeste.commands.sc;
import me.icendev.PluginTeste.commands.coins;
import me.icendev.PluginTeste.events.onplayertag;
import me.icendev.PluginTeste.events.playerinteractentityevent;
import me.icendev.PluginTeste.events.playerinteractevent;
import me.icendev.PluginTeste.events.playerjoinevent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Henrique on 22-05-2017.
 */
public class Main extends JavaPlugin {
    private Connection connection;
    public String pluginName = ChatColor.YELLOW + "[PluginTeste] ";
    public String host,username,password,database;
    public int port;



    public void onEnable(){
        loadConfig();
        mysqlsetup();
        getCommand("coins").setExecutor(new coins());
        getCommand("sc").setExecutor(new sc());
        getServer().getPluginManager().registerEvents(new playerjoinevent(), this);
        getServer().getPluginManager().registerEvents(new playerinteractentityevent(), this);
        getServer().getPluginManager().registerEvents(new playerinteractevent(), this);
        getServer().getPluginManager().registerEvents(new onplayertag(), this);
        Bukkit.getConsoleSender().sendMessage(pluginName + ChatColor.GREEN + "Ativo com sucesso!");
        //getCommand("ver").setExecutor(new ver());

    }

    public void mysqlsetup() {
        host = this.getConfig().getString("host");
        username = this.getConfig().getString("username");
        password = this.getConfig().getString("password");
        database = this.getConfig().getString("database");
        port = this.getConfig().getInt("port");
        try {
            synchronized (this){
                if(getConnection() != null && !getConnection().isClosed()){
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + pluginName + "DataBase not connected!");
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database,this.username,this.password));
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + pluginName + "DataBase connected with success!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onDisable(){
        System.out.println(pluginName + "Desativado!");

    }
    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    public Connection getConnection(){
        return connection;
    }
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    public void comandosCoins(CommandSender sender){
        sender.sendMessage(ChatColor.YELLOW + "--- Comando válidos para coins ---");
        sender.sendMessage(ChatColor.GREEN + "/coins ver <jogador>" + ChatColor.GRAY + "(Usa para consultar os coins de um jogador online)");
        sender.sendMessage(ChatColor.GREEN + "/coins dar <jogador> <quantia>" + ChatColor.GRAY + "(Usa para dar os teus coins a um jogador online)");
        sender.sendMessage(ChatColor.GREEN + "/coins top" + ChatColor.GRAY + "(Usa para veres o top 10 dos jogadores mais ricos)");
        //Comandos Admin
        if(sender.hasPermission("coins.admin")){
            sender.sendMessage(ChatColor.DARK_RED + "--- [Admin] Comando válidos para coins ---");
            sender.sendMessage(ChatColor.GREEN + "/dar admincoins <jogador>" + ChatColor.GRAY + "(Usa para dar coins a outra pessoa)");
            sender.sendMessage(ChatColor.GREEN + "/remover admincoins <jogador>" + ChatColor.GRAY + "(Usa para remover coins a outra pessoa)");
        }

    }
}
