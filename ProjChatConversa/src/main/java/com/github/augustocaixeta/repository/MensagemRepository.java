package com.github.augustocaixeta.repository;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MensagemRepository {
    Connection cn;
    
    public MensagemRepository() {
        cn = new Conexao().obterConexao();
    }
    
    public void salvar(Mensagem mensagem) {
        String sql = "INSERT INTO mensagens (autor_id, conversa_id, conteudo, data_envio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, mensagem.getAutor().getId());
            ps.setInt(2, mensagem.getConversa().getId());
            ps.setString(3, mensagem.getConteudo());
            ps.setTimestamp(4, Timestamp.valueOf(mensagem.getDataEnvio()));
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                mensagem.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Mensagem> buscarPorConversa(int conversaId) {
        List<Mensagem> mensagens = new ArrayList<>();
        Map<Integer, Usuario> usuariosCache = new HashMap<>();
        Conversa conversa = new Conversa(conversaId);
        
        String sql = ""
                + "SELECT "
                + "   m.id,"
                + "   m.conteudo,"
                + "   m.data_envio, "
                + "   u.id AS autor_id,"
                + "   u.nome AS autor_nome "
                + "FROM mensagens AS m "
                + "JOIN usuarios AS u ON u.id = m.autor_id "
                + "WHERE m.conversa_id = ? "
                + "ORDER BY m.data_envio";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, conversaId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int autorId = rs.getInt("autor_id");
                String autorNome = rs.getString("autor_nome");
                
                Usuario autor = usuariosCache.computeIfAbsent(autorId, id ->
                    new Usuario(id, autorNome)
                );
                
                mensagens.add(new Mensagem(
                    rs.getInt("id"), autor, conversa,
                    rs.getString("conteudo"),
                    rs.getTimestamp("data_envio").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return mensagens;
    }
}
