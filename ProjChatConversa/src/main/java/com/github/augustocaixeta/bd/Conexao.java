package com.github.augustocaixeta.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    final private String driver = "com.mysql.cj.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost:3307/bd_chat_conversa";
    final private String usuario = "root";
    final private String senha = "root";
    
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
