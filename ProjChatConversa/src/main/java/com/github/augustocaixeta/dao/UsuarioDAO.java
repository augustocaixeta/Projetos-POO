package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    Connection cn;
    
    public UsuarioDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Usuario salvar(Usuario usuario) {
        String sqlStr = "INSERT INTO usuarios(nome, senha, email, criado_em) VALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getEmail());
            ps.setTimestamp(4, Timestamp.valueOf(usuario.getCriadoEm()));
            
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    
    public List<Usuario> listarUsuarios(int usuarioId) {
        List<Usuario> usuarios = new ArrayList<>();
        String sqlStr = ""
                + "SELECT"
                + "    u.* "
                + "FROM"
                + "    contatos AS c "
                + "JOIN"
                + "    usuarios AS u ON u.id = c.contato_id "
                + "WHERE"
                + "    c.titular_id = ? "
                + "ORDER BY"
                + "    u.nome;";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr)) {
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        rs.getTimestamp("criado_em").toLocalDateTime()
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
    public List<Conversa> listarConversas(int usuarioId) {
        List<Conversa> conversas = new ArrayList<>();
        String sqlStr = ""
                + "SELECT"
                + "    c.*"
                + "FROM" 
                + "    conversas AS c "
                + "JOIN"
                + "    participantes AS p ON p.conversa_id = c.id "
                + "WHERE"
                + "    p.usuario_id = ? "
                + "ORDER BY"
                + "    c.ultima_mensagem_em DESC, "
                + "    c.id;";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr)) {
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Conversa conversa = new Conversa(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getTimestamp("criada_em").toLocalDateTime()
                );
                conversas.add(conversa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conversas;
    }
    
    public Usuario obterUsuarioPeloNome(String nome) {
        String sqlStr = "SELECT * FROM usuarios WHERE nome = ?;";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr)) {
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
