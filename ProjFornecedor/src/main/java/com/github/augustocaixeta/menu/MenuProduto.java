package com.github.augustocaixeta.menu;

import com.github.augustocaixeta.model.Produto;
import com.github.augustocaixeta.service.ProdutoService;

import java.util.Scanner;

public class MenuProduto {
    public static void executar(Scanner scanner) {
        ProdutoService produtoService = new ProdutoService();
        int opcao = -1;
        
        while (opcao != 0) {
            System.out.println("************* MENU PRODUTO *************");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("0. Retornar");
            System.out.print("R: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1 -> {
                    Produto produto = new Produto();
                    System.out.print("Nome: ");
                    produto.setNome(scanner.nextLine());
                    System.out.print("Valor: R$ ");
                    produto.setValor(scanner.nextDouble());
                    produtoService.salvarProduto(produto);
                }
                case 2 -> produtoService.exibirProdutos();
                case 0 -> System.out.print("Retornando ao menu principal...");
                default -> System.out.print("Opcao do menu produto invalida.");
            }
        }
    }
}
