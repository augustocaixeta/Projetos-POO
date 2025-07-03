package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConversaParticipanteDAO {
    Connection cn;
    
    public ConversaParticipanteDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public void salvarIndividual(int conversaId, int usuarioId) {
        String sql = "INSERT INTO conversa_participantes(conversa_id, usuario_id, papel) VALUES (?, ?, 'participante')";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, conversaId);
            ps.setInt(2, usuarioId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
