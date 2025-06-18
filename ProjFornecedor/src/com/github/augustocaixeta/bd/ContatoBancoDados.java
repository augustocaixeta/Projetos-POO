package com.github.augustocaixeta.bd;

import com.github.augustocaixeta.conexao.ConexaoBancoDados;
import com.github.augustocaixeta.classes.Contato;
import com.github.augustocaixeta.classes.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ContatoBancoDados {
    Connection cn = new ConexaoBancoDados().getConnection();
    
    public Contato salvar(Contato contato) {
        String sqlCode = "INSERT INTO fornecedor_contatos(fornecedor_id, tipo, contato) VALUES (?, ?, ?);";
        try (PreparedStatement pdt = cn.prepareStatement(sqlCode, Statement.RETURN_GENERATED_KEYS)) {
            pdt.setInt(1, contato.getFornecedor().getId());
            pdt.setString(2, contato.getTipo());
            pdt.setString(3, contato.getContato());
            int linhasAfetadas = pdt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = pdt.getGeneratedKeys();
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
