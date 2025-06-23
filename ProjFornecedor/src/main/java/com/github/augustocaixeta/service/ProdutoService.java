package com.github.augustocaixeta.service;

import com.github.augustocaixeta.model.Produto;
import com.github.augustocaixeta.dao.ProdutoDAO;

import java.util.List;

public class ProdutoService {
    ProdutoDAO produtoDao;
    
    public ProdutoService() {
        produtoDao = new ProdutoDAO();
    }
    
    public void salvarProduto(Produto produto) {
        produtoDao.salvar(produto);
    }
    
    public void exibirProdutos() {
        List<Produto> produtos = produtoDao.obterListaProdutos();
        
        for (Produto p : produtos) {
            System.out.println(p.toString());
        }
    }
}
