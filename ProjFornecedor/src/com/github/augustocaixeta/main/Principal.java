package com.github.augustocaixeta.main;

import com.github.augustocaixeta.classes.Contato;
import com.github.augustocaixeta.classes.Fornecedor;
import com.github.augustocaixeta.rn.FornecedorRegraNegocio;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        FornecedorRegraNegocio rn = new FornecedorRegraNegocio();
        rn.exibirFornecedores();

        Fornecedor f1 = new Fornecedor("IGOR C.", "87.803.949/0001-08");
        List<Contato> contatos = new ArrayList<>();
        contatos.add(new Contato("TELEFONE", "(34) 3831-1111", f1));
        contatos.add(new Contato("TELEFONE", "(34) 3831-2222", f1));
        contatos.add(new Contato("TELEFONE", "(34) 3831-3333", f1));
        
        // Atribuir lista de contatos ao fornecedor
        f1.setContatos(contatos);
        
        // Salvar
        rn.salvarFornecedor(f1);
        
        // Exibir novamente
        rn.exibirFornecedores();
    }
}
