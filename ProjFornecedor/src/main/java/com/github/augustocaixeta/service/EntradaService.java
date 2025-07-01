package com.github.augustocaixeta.service;

import com.github.augustocaixeta.model.Entrada;
import com.github.augustocaixeta.model.EntradaProduto;
import com.github.augustocaixeta.model.Produto;
import com.github.augustocaixeta.dao.EntradaDAO;
import com.github.augustocaixeta.dao.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;

public class EntradaService {
    EntradaDAO entradaDao;
    ProdutoDAO produtoDao;
    
    public EntradaService() {
        entradaDao = new EntradaDAO();
        produtoDao = new ProdutoDAO();
    }
    
    private void calcularTotalEntrada(Entrada entrada) {
        double total = 0.0;
        for (EntradaProduto ep : entrada.getItensEntrada()) {
            Produto produto = ep.getProduto();
            produtoDao.obterProduto(produto);
            total += produto.getValor() * ep.getQuantidade();
        }
        entrada.setTotalEntrada(total);
    }
    
    private void exibirItens(List<EntradaProduto> entradaProdutos) {
        for (EntradaProduto ep : entradaProdutos) {
            System.out.println(ep.toString());
        }
    }
    
    private void exibirEntradas(List<Entrada> entradas) {
        for (Entrada e : entradas) {
            System.out.println(e.toString());
           
            if (e.getItensEntrada() != null) {
                System.out.println("# Itens Entrada:\n");
                exibirItens(e.getItensEntrada());
            }
            System.out.println("\n************************************\n");
        }
    }
    
    public void salvarEntrada(Entrada entrada) {
        calcularTotalEntrada(entrada);
        entradaDao.salvar(entrada);
        if (entrada.getId() != 0) {
            for (EntradaProduto ep : entrada.getItensEntrada()) {
                
            }
        }
    }
    
    public void exibirEntradas() {
        List<Entrada> entradas = new ArrayList<>();
        EntradaService.this.exibirEntradas(entradas);
    }
}
