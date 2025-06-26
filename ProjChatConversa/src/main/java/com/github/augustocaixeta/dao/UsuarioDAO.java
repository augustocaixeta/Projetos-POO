package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Contato;
import com.github.augustocaixeta.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.Iterator;

public class UsuarioDAO {
    Connection cn;
    
    public UsuarioDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Usuario salvar(Usuario usuario) throws SQLException {
        String sqlUsuario = "INSERT INTO usuarios(nome, email, data_criacao) VALUES (?, ?, ?);";
        String sqlContato = "INSERT INTO contatos(id_fk_titular, id_fk_contato, apelido, data_criacao) VALUES (?, ?, ?, ?);";
        
        cn.setAutoCommit(false);
        
        try (
            PreparedStatement psUsuario = cn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psContato = cn.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS)
        ) {
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            psUsuario.setString(1, usuario.getNome());
            psUsuario.setString(2, usuario.getEmail());
            psUsuario.setTimestamp(3, now);
            psUsuario.executeUpdate();
            
            ResultSet rsUsuario = psUsuario.getGeneratedKeys();
            if (rsUsuario.next()) {
                usuario.setId(rsUsuario.getInt(1));
            }

            for (Contato contato : usuario.getContatos()) {
                psContato.setInt(1, usuario.getId());
                psContato.setInt(2, contato.getContato().getId());
                psContato.setString(3, contato.getApelido());
                psUsuario.setTimestamp(3, now);
                psContato.addBatch();
            }
            
            psContato.executeBatch();
            ResultSet rsContato = psContato.getGeneratedKeys();
            Iterator<Contato> it = usuario.getContatos().iterator();

            while (rsContato.next() && it.hasNext()) {
                Contato contato = it.next();
                contato.setId(rsContato.getInt(1));
            }

            cn.commit();
        } catch (SQLException ex) {
            cn.rollback();
            throw ex;
        } finally {
            cn.setAutoCommit(true);
        }

        return usuario;
    }
    
    public Usuario obterUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getTimestamp("data_criacao").toLocalDateTime()
        );
        return usuario;
    }
}
