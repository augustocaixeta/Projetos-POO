package com.github.augustocaixeta.menu;

import com.github.augustocaixeta.model.Contato;
import com.github.augustocaixeta.model.Fornecedor;
import com.github.augustocaixeta.service.FornecedorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuFornecedor {
    public static void executar(Scanner scanner) {
        FornecedorService fornecedorService = new FornecedorService();
        int opcao = -1;
        
        while (opcao != 0) {
            System.out.println("************* MENU FORNECEDOR/CONTATO *************");
            System.out.println("1. Cadastrar Fornecedor");
            System.out.println("2. Listar Fornecedores");
            System.out.println("0. Retornar");
            System.out.print("R: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1 -> {
                    Fornecedor fornecedor = new Fornecedor();
                    System.out.print("Nome: ");
                    fornecedor.setNome(scanner.nextLine());
                    System.out.print("CNPJ: ");
                    fornecedor.setCNPJ(scanner.nextLine());
                
                    List<Contato> contatos = new ArrayList<>();
                    System.out.print("Quantos contatos deseja adicionar? R: ");
                    int quantidade = scanner.nextInt();
                    
                    for (int i = 0; i < quantidade; i++) {
                        Contato contato = new Contato();
                        System.out.print("Tipo do contato: ");
                        contato.setTipo(scanner.nextLine());
                        System.out.print("Contato: ");
                        contato.setContato(scanner.nextLine());
                        contato.setFornecedor(fornecedor);
                        contatos.add(contato);
                    }
                
                    fornecedor.setContatos(contatos);
                    fornecedorService.salvarFornecedor(fornecedor);
                }
                case 2 -> fornecedorService.exibirFornecedores();
                case 0 -> System.out.print("Retornando ao menu principal...");
                default -> System.out.print("Opcao do menu fornecedor invalida.");
            }
        }
    }
}
