package com.github.augustocaixeta.menu;

import java.util.Scanner;

public class MenuPrincipal {
    public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        
        while (opcao != 0) {
            System.out.println("1. Fornecedor e Contato");
            System.out.println("2. Produto");
            System.out.println("3. Entrada");
            System.out.println("0. Sair");
            System.out.print("R: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1 -> MenuFornecedor.executar(scanner);
                case 2 -> MenuProduto.executar(scanner);
                case 3 -> MenuEntrada.executar(scanner);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opcao invalida do menu principal invalida.");
            }
        }
        scanner.close();
    }
}
