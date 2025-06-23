package com.github.augustocaixeta.menu;

import com.github.augustocaixeta.model.Entrada;
import com.github.augustocaixeta.model.Fornecedor;
import com.github.augustocaixeta.service.EntradaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class MenuEntrada {
    public static void executar(Scanner scanner) {
        EntradaService entradaService = new EntradaService();
        int opcao = -1;
        
        while (opcao != 0) {
            System.out.println("************* MENU ENTRADA *************");
            System.out.println("1. Registrar Entrada");
            System.out.println("2. Listar Entradas");
            System.out.println("0. Retornar");
            System.out.print("R: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1 -> {
                    Entrada entrada = new Entrada();
                    Fornecedor fornecedor = new Fornecedor();
                    
                    System.out.print("ID do Fornecedor: ");
                    fornecedor.setId(scanner.nextInt());
                    entrada.setFornecedor(fornecedor);
                    
                    System.out.print("Data (dd/MM/yyyy): ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataEntrada = LocalDate.parse(scanner.nextLine(), formatter);
                    entrada.setDataEntrada(dataEntrada);
                    
                    
                }
                case 2 -> entradaService.exibirEntradas();
                case 0 -> System.out.print("Retornando ao menu principal...");
                default -> System.out.print("Opcao do menu entrada invalida.");
            }
        }
    }
}
