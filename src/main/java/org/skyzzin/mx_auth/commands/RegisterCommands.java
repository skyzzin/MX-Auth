package org.skyzzin.mx_auth.commands;


import org.bukkit.plugin.java.JavaPlugin;

public class RegisterCommands {
    public RegisterCommands(JavaPlugin plugin) {

        //Instances
        PlayerJoin playerJoin = new PlayerJoin();

        //Commands
        plugin.getCommand("login").setExecutor(new Login(playerJoin));
        plugin.getCommand("register").setExecutor(new Register());

        //Listeners
        plugin.getServer().getPluginManager().registerEvents(playerJoin,plugin);

    }


}
