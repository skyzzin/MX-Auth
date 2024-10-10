package org.skyzzin.mx_auth;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.skyzzin.mx_auth.commands.PlayerJoin;
import org.skyzzin.mx_auth.commands.RegisterCommands;
import org.skyzzin.mx_auth.util.Conn;

import java.sql.SQLException;

public final class Mx_auth extends JavaPlugin implements Listener {


    private Conn conn;

    @Override
    public void onEnable() {
        try {
            conn = new Conn();

            new RegisterCommands(this );

        } catch (SQLException e) {
            getLogger().severe("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        if (conn != null) {
            try {
                conn.dropTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            conn.closeConnection();
        }
    }
}
