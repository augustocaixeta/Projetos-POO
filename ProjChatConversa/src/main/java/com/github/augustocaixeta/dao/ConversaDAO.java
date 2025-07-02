package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Mensagem;
import com.github.augustocaixeta.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class ConversaDAO {
    Connection cn;
    
    public ConversaDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Conversa salvar(Conversa conversa) {
        String sql = "INSERT INTO conversas(tipo, criada_em) VALUES (?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, conversa.getTipo());
            ps.setTimestamp(2, Timestamp.valueOf(conversa.getCriadaEm()));
            
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    conversa.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return conversa;
    }
    
    public Conversa buscarConversaIndividualEntre(int usuarioId1, int usuarioId2) {
        String sql = ""
                + "SELECT "
                + "    c.* "
                + "FROM "
                + "    conversas AS c "
                + "JOIN "
                + "    conversa_participantes AS cp1 ON cp1.conversa_id = c.id AND cp1.usuario_id = ? "
                + "JOIN "
                + "    conversa_participantes AS cp2 ON cp1.conversa_id = c.id AND cp2.usuario_id = ? "
                + "WHERE "
                + "    c.tipo = 'individual';";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, usuarioId1);
            ps.setInt(2, usuarioId2);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Conversa conversa = new Conversa(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getTimestamp("criada_em").toLocalDateTime()
                );
                return conversa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Mensagem> listarConversaMensagens(Conversa conversa) {
        Usuario usuario = new Usuario();
        Mensagem mensagem = new Mensagem();
        List<Mensagem> mensagens = new ArrayList<>();
        
        String sql = ""
                + "SELECT "
                + "    u.id AS usuario_id, u.nome, u.email, u.criado_em, "
                + "    m.id AS mensagem_id, m.conteudo, m.enviada_em "
                + "FROM "
                + "    mensagens AS m "
                + "JOIN "
                + "    usuarios AS u ON u.id = m.autor_id "
                + "WHERE "
                + "    m.conversa_id = ? "
                + "ORDER BY "
                + "    m.enviada_em;";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, conversa.getId());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                usuario.setId(rs.getInt("usuario_id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCriacao(rs.getTimestamp("criado_em").toLocalDateTime());
                
                mensagem.setAutor(usuario);
                mensagem.setConversa(conversa);
                mensagem.setId(rs.getInt("mensagem_id"));
                mensagem.setConteudo(rs.getString("conteudo"));
                mensagem.setEnviadaEm(rs.getTimestamp("enviada_em").toLocalDateTime());
                mensagens.add(mensagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensagens;
    }
    
    public List<Usuario> listarConversaUsuarios(Conversa conversa) {
        Usuario usuario = new Usuario();
        List<Usuario> usuarios = new ArrayList<>();
        
        String sql = ""
                + "SELECT "
                + "    u.* "
                + "FROM"
                + "    conversa_participantes AS cp "
                + "JOIN"
                + "    usuarios AS u ON u.id = cp.usuario_id "
                + "JOIN"
                + "    conversas AS c ON c.id = cp.conversa_id "
                + "WHERE"
                + "    cp.conversa_id = ?;";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, conversa.getId());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCriacao(rs.getTimestamp("criado_em").toLocalDateTime());
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
