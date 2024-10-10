package org.skyzzin.mx_auth.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public class Conn {
    private final Connection conn;
    private final Statement statement;

    public Conn() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:sqlite:plugins/MX-AUTH2.db");
        this.statement = conn.createStatement();

        try {
            statement.execute("CREATE TABLE IF NOT EXISTS players(" +
                    "nickname TEXT UNIQUE," +
                    "password TEXT," +
                    "last_time_enter TEXT," +
                    "id TEXT" +
                    ")");
            Bukkit.getLogger().info(ChatColor.GREEN + "MX-AUTH Database ON");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void dropTable() throws SQLException {
        try {
            statement.execute("DROP TABLE IF EXISTS players");
            Bukkit.getLogger().info(ChatColor.RED + "Tabela 'players' removida com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getLogger().severe(ChatColor.RED + "Erro ao tentar remover a tabela 'players'.");
        }
    }


    public boolean createUser(String nickname, String password) throws SQLException {

        PreparedStatement query = conn.prepareStatement("INSERT INTO players(nickname, password, last_time_enter, id) VALUES (?, ?, ?, ?)");

        query.setString(1, nickname);
        query.setString(2, password);
        query.setString(3, LocalDate.now().toString());
        query.setString(4, String.valueOf(UUID.randomUUID()));

        int affectedRows = query.executeUpdate();

        query.close();
        return affectedRows > 0;
    }

    public void updateDataTime(String nickname) throws SQLException {
        PreparedStatement query = conn.prepareStatement("UPDATE players SET last_time_enter = ? WHERE nickname = ?");
        query.setString(1, LocalDate.now().toString());
        query.setString(2, nickname);

        int affectedRows = query.executeUpdate();

        if (affectedRows > 0) {
            System.out.println("Data de entrada atualizada com sucesso para o jogador: " + nickname);
        } else {
            System.out.println("Jogador não encontrado: " + nickname);
        }

        query.close();
    }

    public boolean Login(String nickname, String password) throws SQLException {
        PreparedStatement query = conn.prepareStatement("SELECT * FROM players WHERE nickname = ? AND password = ?");
        query.setString(1, nickname);
        query.setString(2, password);

        ResultSet resultSet = query.executeQuery();

        boolean jogadorExiste = resultSet.next();

        resultSet.close();
        query.close();
        return jogadorExiste;
    }

    public boolean isPlayer(String nickname) throws SQLException {
        PreparedStatement query = conn.prepareStatement("SELECT * FROM players WHERE nickname = ?");
        query.setString(1, nickname);

        ResultSet resultSet = query.executeQuery();

        boolean jogadorExiste = resultSet.next();

        resultSet.close();
        query.close();
        return jogadorExiste;
    }

    public void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
