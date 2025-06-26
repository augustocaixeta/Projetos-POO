package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Mensagem;
import com.github.augustocaixeta.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO {
    Connection cn;
    
    public MensagemDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Mensagem salvar(Mensagem mensagem) {
        String sql = "INSERT INTO mensagens(id_fk_autor, id_fk_conversa, conteudo, data_enviada) VALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, mensagem.getAutor().getId());
            ps.setInt(2, mensagem.getConversa().getId());
            ps.setString(3, mensagem.getConteudo());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    mensagem.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mensagem;
    }
    
    public List<Mensagem> obterListaConversaMensagens(Conversa conversa) throws SQLException {
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM mensagens "
                + "JOIN usuarios id_usuario = id_fk_autor "
                + "WHERE id_fk_conversa = ? "
                + "ORDER BY data_envio;";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, conversa.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getTimestamp("data_criacao").toLocalDateTime()
                );
                
                Mensagem mensagem = new Mensagem(
                        rs.getInt("id_mensagem"), usuario, conversa,
                        rs.getString("conteudo"),
                        rs.getTimestamp("data_envio").toLocalDateTime()
                );
                
                mensagens.add(mensagem);
            }
        }
        return mensagens;
    }
    
    public Mensagem obterMensagem(ResultSet rs, Usuario autor, Conversa conversa) throws SQLException {
        Mensagem mensagem = new Mensagem(
            rs.getInt("id_mensagem"),
            autor,
            conversa,
            rs.getString("conteudo"),
            rs.getTimestamp("data_envio").toLocalDateTime()
        );
        return mensagem;
    }
}
