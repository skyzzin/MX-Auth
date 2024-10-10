package org.skyzzin.mx_auth.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.skyzzin.mx_auth.util.Conn;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerJoin implements Listener {

    private final Set<UUID> jogadoresTravados = new HashSet<>();
    private final Map<UUID, Location> localizacoesOriginais = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        // Salva a localização original apenas se o jogador não estiver travado
        if (!jogadoresTravados.contains(playerId)) {
            Location localizacaoOriginal = player.getLocation();
            localizacoesOriginais.put(playerId, localizacaoOriginal);
        }

        // Permite voo temporariamente para evitar kick por fly
        player.setAllowFlight(true);
        player.setFlying(true);

        // Configurações para travar o jogador no lugar
        player.setWalkSpeed(0);
        player.setFlySpeed(0);
        player.setGravity(false); // Desativa a gravidade para evitar queda

        // Adiciona o jogador à lista de jogadores travados
        jogadoresTravados.add(playerId);

        // Teletransporta o jogador para a posição atual, mas com o Y em 999
        Location novaLocalizacao = player.getLocation().clone();
        novaLocalizacao.setY(999);
        player.teleport(novaLocalizacao);

        // Mensagem de registro ou login
        if (!new Conn().isPlayer(player.getName())) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "Crie Sua Conta: " + ChatColor.AQUA + " /register senha senha"));
            player.sendMessage(ChatColor.GOLD + "Crie Sua Conta " + ChatColor.AQUA + " /register senha senha");
        } else {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "Entre Na Sua Conta: " + ChatColor.AQUA + " /login senha"));
            player.sendMessage(ChatColor.GOLD + "Entre Na Sua Conta: " + ChatColor.AQUA + " /login senha");
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (jogadoresTravados.contains(player.getUniqueId())) {
            Location from = event.getFrom();
            Location to = event.getTo();

            if (from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ()) {
                event.setTo(from); // Teletransporta o jogador de volta para a posição anterior
            }
        }
    }

    public void destravarJogador(Player player) {
        UUID playerId = player.getUniqueId();

        // Remove o jogador da lista de travados
        jogadoresTravados.remove(playerId);

        // Restaura as configurações padrão de movimento
        player.setWalkSpeed(0.2F); // Velocidade padrão de caminhada
        player.setFlySpeed(0.1F); // Velocidade padrão de voo
        player.setGravity(true); // Ativa a gravidade novamente

        // Desativa o voo para o jogador
        player.setAllowFlight(false);
        player.setFlying(false);

        // Teletransporta o jogador de volta para a posição original
        Location localizacaoOriginal = localizacoesOriginais.get(playerId);
        if (localizacaoOriginal != null) {
            player.teleport(localizacaoOriginal);
            localizacoesOriginais.remove(playerId); // Remove a localização original após o uso
        }
    }
}
