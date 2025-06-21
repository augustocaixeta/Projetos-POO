package com.github.augustocaixeta.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/poo";
    private final String username = "root";
    private final String password = "root";

    public Connection obterConexao() {
        Connection cn = null;

        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return cn;
    }
}
