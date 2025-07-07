package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Mensagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class MensagemDAO {
    Connection cn;
    
    public MensagemDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Mensagem salvar(Mensagem mensagem) {
        String sqlStr = "INSERT INTO mensagens(autor_id, conversa_id, conteudo, enviada_em) VALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, mensagem.getAutor().getId());
            ps.setInt(2, mensagem.getConversa().getId());
            ps.setString(3, mensagem.getConteudo());
            ps.setTimestamp(4, Timestamp.valueOf(mensagem.getEnviadaEm()));
            
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    mensagem.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensagem;
    }
}
