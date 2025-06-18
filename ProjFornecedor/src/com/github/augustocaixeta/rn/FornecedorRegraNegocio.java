package com.github.augustocaixeta.rn;

import com.github.augustocaixeta.bd.ContatoBancoDados;
import com.github.augustocaixeta.bd.FornecedorBancoDados;
import com.github.augustocaixeta.classes.Contato;
import com.github.augustocaixeta.classes.Fornecedor;

import java.util.List;

public class FornecedorRegraNegocio {
    FornecedorBancoDados fbd = new FornecedorBancoDados();
    ContatoBancoDados cbd = new ContatoBancoDados();
    
    private void exibirFornecedores(List<Fornecedor> fornecedores) {
        for (Fornecedor fornecedor : fornecedores) {
            System.out.println("ID: " + fornecedor.getId() + "\n"
                    + "Nome: " + fornecedor.getNome() + "\n"
                    + "CNPJ: " + fornecedor.getCNPJ());
            
            if (fornecedor.getContatos() != null) {
                System.out.println("\n# Contatos:\n");
                exibirContatos(fornecedor.getContatos());
            }
            System.out.println("\n************************************\n");
        }
    }
    
    private void exibirContatos(List<Contato> contatos) {
        for (Contato contato : contatos) {
            System.out.println("ID: " + contato.getId() + "\n"
                    + "Tipo: " + contato.getTipo() + "\n"
                    + "Contato: " + contato.getContato() + "\n"
                    + "Fornecedor: " + contato.getFornecedor().getId() + "\n");
        }
    }
    
    public void salvarFornecedor(Fornecedor fornecedor) {
        fbd.salvar(fornecedor);
        
        if (fornecedor.getContatos() != null && !fornecedor.getContatos().isEmpty()) {
            // fornecedor.getContatos().forEach(c -> cbd.salvar(c));
            for (Contato contato : fornecedor.getContatos()) {
                cbd.salvar(contato);
            }
        }
    }
    
    public void exibirFornecedores() {
        List<Fornecedor> fornecedores = fbd.obterListaFornecedorContatos();
        exibirFornecedores(fornecedores);
    }
}
