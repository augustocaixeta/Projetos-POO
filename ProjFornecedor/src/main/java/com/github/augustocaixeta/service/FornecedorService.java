package com.github.augustocaixeta.service;

import com.github.augustocaixeta.dao.ContatoDAO;
import com.github.augustocaixeta.dao.FornecedorDAO;
import com.github.augustocaixeta.model.Contato;
import com.github.augustocaixeta.model.Fornecedor;

import java.util.List;

public class FornecedorService {
    FornecedorDAO fornecedorDao;
    ContatoDAO contatoDao;
    
    public FornecedorService() {
        fornecedorDao = new FornecedorDAO();
        contatoDao = new ContatoDAO();
    }
    
    private void exibirFornecedores(List<Fornecedor> fornecedores) {
        for (Fornecedor f : fornecedores) {
            System.out.println(f.toString());
            
            if (f.getContatos() != null) {
                System.out.println("# Contatos:\n");
                exibirContatos(f.getContatos());
            }
            System.out.println("\n************************************\n");
        }
    }
    
    public void salvarFornecedor(Fornecedor fornecedor) {
        fornecedorDao.salvar(fornecedor);
        
        if (fornecedor.getContatos() != null && !fornecedor.getContatos().isEmpty()) {
            // fornecedor.getContatos().forEach(c -> cbd.salvar(c));
            for (Contato contato : fornecedor.getContatos()) {
                contatoDao.salvar(contato);
            }
        }
    }
    
    private void exibirContatos(List<Contato> contatos) {
        for (Contato c : contatos) {
            System.out.println(c.toString());
        }
    }
    
    public void exibirFornecedores() {
        List<Fornecedor> fornecedores = fornecedorDao.obterListaFornecedorContatos();
        exibirFornecedores(fornecedores);
    }
}
