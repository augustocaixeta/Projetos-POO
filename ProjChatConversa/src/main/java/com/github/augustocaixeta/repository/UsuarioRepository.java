package com.github.augustocaixeta.repository;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class UsuarioRepository {
    Connection cn;
    
    public UsuarioRepository() {
        cn = new Conexao().obterConexao();
    }
    
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, criacao_dt) VALUES (?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setTimestamp(3, Timestamp.valueOf(usuario.getDataCriacao()));
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletar(int usuarioId) {
        String sql = "DELETE FROM usuarios WHERE id = ?;";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
