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

public class FornecedorBancoDados {
    Connection cn = new ConexaoBancoDados().getConnection();
    ContatoBancoDados cbd = new ContatoBancoDados();
    
    private Fornecedor obterFornecedor(ResultSet rs) throws SQLException {
        Fornecedor fornecedor = new Fornecedor(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cnpj")
        );
        return fornecedor;
    }
    
    public List<Fornecedor> salvar() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sqlCode = "SELECT * FROM fornecedores;";
        try (Statement stmt = cn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlCode);
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj")
                );
                fornecedores.add(fornecedor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fornecedores;
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        String sqlCode = "INSERT INTO fornecedores(nome, cnpj) VALUES (?, ?);";
        try (PreparedStatement pdt = cn.prepareStatement(sqlCode, Statement.RETURN_GENERATED_KEYS)) {
            pdt.setString(1, fornecedor.getNome());
            pdt.setString(2, fornecedor.getCNPJ());
            int linhasAfetadas = pdt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = pdt.getGeneratedKeys();
                if (rs.next()) {
                    fornecedor.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fornecedor;
    }
    
    public List<Fornecedor> obterListaFornecedores() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sqlCode = "SELECT * FROM fornecedores;";
        try (PreparedStatement pdt = cn.prepareStatement(sqlCode)) {
            ResultSet rs = pdt.executeQuery();
            while (rs.next()) {
                fornecedores.add(obterFornecedor(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fornecedores;
    }
    
    public List<Fornecedor> obterListaFornecedorContatos() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sqlCode = ""
                + "SELECT "
                + "    f.*, "
                + "    fc.id AS contato_id, "
                + "    fc.tipo AS contato_tipo, "
                + "    fc.contato AS contato_conteudo "
                + "FROM fornecedores AS f "
                + "LEFT JOIN fornecedor_contatos AS fc ON fc.fornecedor_id = f.id;";
        try (PreparedStatement pdt = cn.prepareStatement(sqlCode)) {
            ResultSet rs = pdt.executeQuery();
            int ultimoId = -1;
            Fornecedor ultimoFornecedor = null;

            while (rs.next()) {
                int fornecedorId = rs.getInt("id");
                if (fornecedorId != ultimoId) {
                    ultimoFornecedor = obterFornecedor(rs);
                    ultimoFornecedor.setContatos(new ArrayList<>());
                    fornecedores.add(ultimoFornecedor);
                    ultimoId = fornecedorId;
                }
                
                int contatoId = rs.getInt("contato_id");
                if (contatoId != 0 && ultimoFornecedor != null) {
                    Contato contato = cbd.obterContato(rs, ultimoFornecedor);
                    ultimoFornecedor.getContatos().add(contato);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fornecedores;
    }
}
