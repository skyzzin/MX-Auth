package org.skyzzin.mx_auth.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.skyzzin.mx_auth.util.Conn;

import java.sql.SQLException;

public class Register implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;

            String password1 = args[0];
            String password2 = args[1];

            if(password1.equals(password2))
            {
                try {
                    if(new Conn().createUser(player.getName(),password1)){
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "Conta Criada Com Sucesso: " + ChatColor.AQUA + "/login senha"));
                        player.sendMessage(ChatColor.GOLD + "Entre Na Sua Conta: " + ChatColor.AQUA + " /login senha");

                    }
                } catch (SQLException e) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Usuario Já Registrado: " + ChatColor.AQUA + "/login senha"));
                    player.sendMessage(ChatColor.RED + "Usuario Já Cadastrado: " + ChatColor.AQUA + " /login senha");
                }

            }

        }
        return true;
    }
}

