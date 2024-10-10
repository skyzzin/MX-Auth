package org.skyzzin.mx_auth.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.skyzzin.mx_auth.util.Conn;

import java.sql.SQLException;

public class Login implements CommandExecutor {

    private PlayerJoin playerJoin;

    public Login(PlayerJoin _playerJoin) {
        playerJoin = _playerJoin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "Por favor, forneça a senha.");
                return false;
            }

            String password = args[0];

            try {
                if (new Conn().Login(player.getName(), password)) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "Usuario Logado Com Sucesso: " + ChatColor.AQUA + "Agora Você Pode Se Movimenta"));

                    playerJoin.destravarJogador(player);
                } else {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Senha Incorreta: " + ChatColor.AQUA + "/login senha"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Erro Ao Tenta Logar: " + ChatColor.AQUA + "MX-AUTH ERROR PLUGIN"));
            }

        } else {
            sender.sendMessage("Esse comando só pode ser usado por jogadores.");
        }
        return true;
    }
}

