package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Conversa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConversaDAO {
    Connection cn;
    
    public ConversaDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Conversa obterConversa(ResultSet rs) throws SQLException {
        Conversa conversa = new Conversa(
            rs.getInt("id_conversa"),
            rs.getString("tipo"),
            rs.getTimestamp("data_conversa").toLocalDateTime()
        );
        return conversa;
    }
}
