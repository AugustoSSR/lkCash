package com.developer.lk.cash.mysql;

import com.developer.lk.api.server.ServerAPI;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Metodos extends Conexao {


    public static void adicionarJogador(Player jogador) {
        try {

            Connection con = abrirConexao();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `lb_cash` VALUES(?,?)");
            stmt.setString(2, jogador.getName());
            stmt.setDouble(1, 0);
            stmt.executeUpdate();

            ServerAPI.getConsole().sendMessage("§aJogador adicionado no banco de dados.");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean hasJogador(Player jogador) {
        try {
            try {
                Connection con = abrirConexao();
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM `lb_cash` WHERE `nome` = ?");
                stmt.setString(1, jogador.getName());
                ResultSet rs = stmt.executeQuery();

                boolean result = rs.next();

                ServerAPI.getConsole().sendMessage("§aJogador encontrado no banco de dados.");

                con.close();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getCash(Player jogador) {
        int value = 0;
        try {
            try {
                Connection con = abrirConexao();
                PreparedStatement stmt = con.prepareStatement("SELECT `cash` FROM `lb_cash` WHERE `nome` = ?");
                stmt.setString(1, jogador.getName());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    value = rs.getInt("cash");
                }

                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void setCash(Player jogador, int cash) {
        if (hasJogador(jogador)) {
            try {

                Connection con = abrirConexao();
                PreparedStatement stmt = con.prepareStatement("UPDATE `lb_cash` SET `cash` = ? WHERE `nome` = ?");
                stmt.setInt(1, Integer.valueOf(cash));
                stmt.setString(2, jogador.getName());
                stmt.executeUpdate();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            adicionarJogador(jogador);
        }
    }

    public static void addCash(Player jogador, int cash) {

        if (hasJogador(jogador)) {
            setCash(jogador, getCash(jogador) + cash);
        } else {
            adicionarJogador(jogador);
        }

    }

    public static void removeCash(Player jogador, int cash) {

        if (hasJogador(jogador)) {
            setCash(jogador, getCash(jogador) - cash);
        } else {
            adicionarJogador(jogador);
        }

    }
}
