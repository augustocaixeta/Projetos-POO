package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Grupo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GrupoDAO {
    Connection cn;
    
    public GrupoDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Grupo salvar(Grupo grupo) {
        String sql = "INSERT INTO grupos(nome, criador_id, conversa_id, criado_em) VALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, grupo.getNome());
            ps.setInt(2, grupo.getCriador().getId());
            ps.setInt(3, grupo.getConversa().getId());
            ps.setTimestamp(4, Timestamp.valueOf(grupo.getDataCriacao()));
            
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    grupo.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupo;
    }
}
