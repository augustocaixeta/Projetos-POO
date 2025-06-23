package com.github.augustocaixeta.dao;

import com.github.augustocaixeta.conexao.Conexao;
import com.github.augustocaixeta.model.Entrada;
import com.github.augustocaixeta.model.EntradaProduto;
import com.github.augustocaixeta.model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

public class EntradaDAO {
    Connection cn;
    
    public EntradaDAO() {
        cn = new Conexao().obterConexao();
    }
    
    private Entrada obterEntrada(ResultSet rs) throws SQLException {
        Entrada entrada = new Entrada(
                rs.getInt("id_entrada"),
                rs.getInt("total_entrada"),
                rs.getDate("data_entrada").toLocalDate()
        );
        
        entrada.setFornecedor(new Fornecedor(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cnpj")
        ));
        
        return entrada;
    }
    
    public Entrada salvar(Entrada entrada) {
        String sql = "INSERT INTO fornecedor_entradas(id_fornecedor, total_entrada, data_entrada) VALUES (?, ?, ?);";
        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, entrada.getFornecedor().getId());
            ps.setDouble(2, entrada.getTotalEntrada());
            ps.setDate(3, Date.valueOf(entrada.getDataEntrada()));
            
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    entrada.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entrada;
    }
    
    public List<Entrada> obterEntradas() {
        List<Entrada> entradas = new ArrayList<>();
        List<EntradaProduto> entradaProdutos = new ArrayList<>();
        
        String sql = ""
                + "SELECT "
                + "    f.*, "
                + "    fe.id AS id_entrada, "
                + "    fe.total AS total_entrada, "
                + "    fe.data AS data_entrada, "
                + "    p.id AS id_produto, "
                + "    p.nome AS nome_produto, "
                + "    p.valor AS valor_produto "
                + "FROM fornecedores AS f "
                + "JOIN fornecedor_entradas AS fe ON fe.id_fornecedor = f.id "
                + "JOIN entrada_produtos AS ep ON ep.id_entrada = fe.id "
                + "JOIN produtos AS p ON p.id = ep.id_produto;";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Entrada entrada = obterEntrada(rs);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return entradas;
    }
}
