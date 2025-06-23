package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.conexao.Conexao;
import com.github.augustocaixeta.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    Connection cn;
    
    public ProdutoDAO() {
        cn = new Conexao().obterConexao();
    }
    
    public Produto salvar(Produto produto) {
        String sql = "INSERT INTO produtos(nome, valor) VALUES (?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getValor());
            
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    produto.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produto;
    }
    
    public List<Produto> obterListaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        
        String sql = "SELECT * FROM produtos;";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                produtos.add(obterProduto(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produtos;
    }
    
    private Produto obterProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDouble("valor")
        );
        return produto;
    }
}
