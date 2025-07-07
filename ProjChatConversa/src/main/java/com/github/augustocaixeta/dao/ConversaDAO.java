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
        String sqlStr = "INSERT INTO conversas(tipo, criada_em) VALUES (?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS)) {
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
    
    public void salvarIndividual(int conversaId, int usuarioId) {
        String sqlStr = "INSERT INTO participantes(conversa_id, usuario_id) VALUES (?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr)) {
            ps.setInt(1, conversaId);
            ps.setInt(2, usuarioId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Conversa buscarConversaPessoal(int usuarioId) {
        String sqlStr = ""
            + "SELECT"
            + "    c.* "
            + "FROM"
            + "    conversas c "
            + "JOIN"
            + "    participantes p ON p.conversa_id = c.id "
            + "WHERE"
            + "    c.tipo = 'Pessoal' AND p.usuario_id = ? "
            + "LIMIT 1;";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr)) {
            ps.setInt(1, usuarioId);
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
    
    public Conversa buscarConversaIndividual(int usuarioId1, int usuarioId2) {
        String sqlStr = ""
                + "SELECT"
                + "    c.* "
                + "FROM"
                + "    conversas AS c "
                + "JOIN"
                + "    participantes AS p1 ON p1.conversa_id = c.id AND p1.usuario_id = ? "
                + "JOIN"
                + "    participantes AS p2 ON p1.conversa_id = c.id AND p2.usuario_id = ? "
                + "WHERE"
                + "    c.tipo = 'Individual';";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr)) {
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
    
    public List<Mensagem> listarMensagens(Conversa conversa) {
        List<Mensagem> mensagens = new ArrayList<>();
        String sqlStr = ""
                + "SELECT"
                + "    u.id AS usuario_id, u.nome, u.email, u.criado_em, "
                + "    m.id AS mensagem_id, m.conteudo, m.enviada_em "
                + "FROM"
                + "    mensagens AS m "
                + "JOIN"
                + "    usuarios AS u ON u.id = m.autor_id "
                + "WHERE"
                + "    m.conversa_id = ? "
                + "ORDER BY"
                + "    m.enviada_em;";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr)) {
            ps.setInt(1, conversa.getId());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("usuario_id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        rs.getTimestamp("criado_em").toLocalDateTime()
                );
               
                Mensagem mensagem = new Mensagem(
                        rs.getInt("mensagem_id"),
                        usuario,
                        conversa,
                        rs.getString("conteudo"),
                        rs.getTimestamp("enviada_em").toLocalDateTime()
                );
                mensagens.add(mensagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensagens;
    }
    
    public List<Usuario> listarUsuarios(Conversa conversa) {
        List<Usuario> usuarios = new ArrayList<>();
        String sqlStr = ""
                + "SELECT"
                + "    u.* "
                + "FROM"
                + "    participantes AS p "
                + "JOIN"
                + "    usuarios AS u ON u.id = p.usuario_id "
                + "JOIN"
                + "    conversas AS c ON c.id = p.conversa_id "
                + "WHERE"
                + "    p.conversa_id = ?;";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr)) {
            ps.setInt(1, conversa.getId());
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
}
