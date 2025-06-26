package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Sala;
import com.github.augustocaixeta.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {
    Connection cn;
    
    public SalaDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Sala salvar(Sala sala) {
        String sql = "INSERT INTO salas(id_fk_usuario, id_fk_conversa, administrador, saiu, data_entrada) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, sala.getUsuario().getId());
            ps.setInt(2, sala.getConversa().getId());
            ps.setBoolean(3, sala.isAdministrador());
            ps.setBoolean(4, sala.isSaiu());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    sala.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sala;
    }
    
    public List<Usuario> listarSalaUsuarios(Sala sala) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM salas "
                + "JOIN usuarios ON id_usuario = id_fk_usuario "
                + "WHERE id_sala = ?;";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, sala.getId());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.obterUsuario(rs);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }
}
