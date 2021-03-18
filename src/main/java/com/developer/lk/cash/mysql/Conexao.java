package com.developer.lk.cash.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Conexao {

    public static Connection abrirConexao(){
        try {
            String user = "root";
            String pass = "";
            String port = "3306";
            String host = "localhost";
            String database = "lb_server";
            String type = "jdbc:mysql://";
            String url = type+host+":"+port+"/"+database;

            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void criarTabela() {

        try {

            Connection con = abrirConexao();
            PreparedStatement stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS lb_cash(cash INT,nome VARCHAR(24));");
            stmt.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
