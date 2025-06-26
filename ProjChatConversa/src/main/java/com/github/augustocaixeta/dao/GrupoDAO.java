package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.bd.Conexao;
import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Grupo;
import com.github.augustocaixeta.model.Mensagem;
import com.github.augustocaixeta.model.Sala;
import com.github.augustocaixeta.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {
    Connection cn;
    
    public GrupoDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Grupo salvar(Grupo grupo, String nome, Usuario criador, Sala sala) {
        String sql = "INSERT INTO grupos(nome, id_fk_criador, id_fk_sala, data_criacao) VALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setInt(2, criador.getId());
            ps.setInt(3, sala.getId());
            ps.setTimestamp(4, Timestamp.valueOf(grupo.getDataCriacao()));
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    grupo.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return grupo;
    }
    
    public List<Mensagem> listarGrupoMensagens(Grupo grupo) {
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = ""
                + "SELECT "
                + "    m.id_mensagem, m.conteudo, m.data_envio, "
                + "    u.id_usuario, u.nome, u.email, u.data_criacao, "
                + "    c.id_conversa, c.tipo, c.data_criacao AS data_conversa "
                + "FROM grupos AS g "
                + "JOIN salas AS s ON s.id_sala = g.id_fk_sala "
                + "JOIN conversas AS c ON c.id_conversa = s.id_fk_conversa "
                + "JOIN mensagens AS m ON m.id_fk_conversa = c.id_conversa "
                + "JOIN usuarios AS u ON u.id_usuario = m.id_fk_autor "
                + "WHERE g.id_grupo = ? "
                + "ORDER BY m.data_envio";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, grupo.getId());
            ResultSet rs = ps.executeQuery();
            
            UsuarioDAO usuarioDao = new UsuarioDAO();
            ConversaDAO conversaDao = new ConversaDAO();
            MensagemDAO mensagemDao = new MensagemDAO();
            
            while (rs.next()) {
                Usuario autor = usuarioDao.obterUsuario(rs);
                Conversa conversa = conversaDao.obterConversa(rs);
                mensagens.add(mensagemDao.obterMensagem(rs, autor, conversa));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mensagens;
    }
}
