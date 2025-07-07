package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class ContatoDAO {
    Connection cn;
    
    public ContatoDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Contato salvar(Contato contato) {
        String sqlStr = "INSERT INTO contatos(titular_id, contato_id, adicionado_em) VALUES (?, ?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, contato.getTitular().getId());
            ps.setInt(2, contato.getContato().getId());
            ps.setTimestamp(3, Timestamp.valueOf(contato.getAdicionadoEm()));
            
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    contato.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contato;
    }
}
