package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.conexao.Conexao;
import com.github.augustocaixeta.model.Contato;
import com.github.augustocaixeta.model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    Connection cn;
    
    public ContatoDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Contato salvar(Contato contato) {
        String sqlCode = "INSERT INTO fornecedor_contatos(id_fornecedor, tipo, contato) VALUES (?, ?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sqlCode, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, contato.getFornecedor().getId());
            ps.setString(2, contato.getTipo());
            ps.setString(3, contato.getContato());
            
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    contato.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contato;
    }
    
    public List<Contato> obterListaContatos() {
        List<Contato> contatos = new ArrayList<>();
        String sqlCode = "SELECT * FROM fornecedor_contatos;";
        try (PreparedStatement pdt = cn.prepareStatement(sqlCode)) {
            ResultSet rs = pdt.executeQuery();
            while (rs.next()) {
                contatos.add(obterContato(rs, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contatos;
    }
    
    public Contato obterContato(ResultSet rs, Fornecedor fornecedor) throws SQLException {
        Contato contato = new Contato(
                rs.getInt("contato_id"),
                rs.getString("contato_tipo"),
                rs.getString("contato_conteudo"),
                fornecedor
        );
        return contato;
    }
}
