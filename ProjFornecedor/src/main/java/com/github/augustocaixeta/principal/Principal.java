package com.github.augustocaixeta.principal;

import com.github.augustocaixeta.service.FornecedorService;
import com.github.augustocaixeta.service.ProdutoService;

public class Principal {
    public static void main(String[] args) {
        FornecedorService fs = new FornecedorService();
        ProdutoService ps = new ProdutoService();
        
        /*
        
        ps.salvarProduto(new Produto("Arroz", 29.90));
        ps.salvarProduto(new Produto("Feijao", 14.90));
        ps.salvarProduto(new Produto("Macarrao", 12.90));
        
        */
        
        fs.exibirFornecedores();
        ps.exibirProdutos();
        
        /*
        
        Fornecedor f1 = new Fornecedor("IGOR C.", "87.803.949/0001-08");
        List<Contato> contatos = new ArrayList<>();
        contatos.add(new Contato("TELEFONE", "(34) 3831-1111", f1));
        contatos.add(new Contato("TELEFONE", "(34) 3831-2222", f1));
        contatos.add(new Contato("TELEFONE", "(34) 3831-3333", f1));
        
        // Atribuir lista de contatos ao fornecedor
        f1.setContatos(contatos);
        
        // Salvar
        fs.salvarFornecedor(f1);
        
        // Exibir novamente
        fs.exibirFornecedores();
    
        */
    }
}
