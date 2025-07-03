package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Contato;
import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsuarioDAO {
    Connection cn;
    
    public UsuarioDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Usuario salvar(Usuario usuario) throws SQLException {
        String sqlUsuario = "INSERT INTO usuarios(nome, email, criado_em) VALUES (?, ?, ?);";
        String sqlContato = "INSERT INTO contatos(usuario_id, contato_id, apelido, adicionado_em) VALUES (?, ?, ?, ?);";
        
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
        } catch (SQLException e) {
            cn.rollback();
            throw e;
        } finally {
            cn.setAutoCommit(true);
        }

        return usuario;
    }
    
    public List<Conversa> listarUsuarioConversas(Usuario usuario) {
        List<Conversa> conversas = new ArrayList<>();
        String sql = "SELECT .. FROM usuarios JOIN conversas;";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return conversas;
    }
    
    public Usuario obterUsuarioPeloNome(String nome) {
        String sql = "SELECT * FROM usuarios WHERE nome = ?;";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        rs.getTimestamp("criado_em").toLocalDateTime()
                );
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
