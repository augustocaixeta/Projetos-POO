package com.github.augustocaixeta.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/bd_chat_conversa";
    private final String usuario = "root";
    private final String senha = "root";
    
    public Connection obterConexao() {
        Connection cn = null;
        
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return cn;
    }
}
