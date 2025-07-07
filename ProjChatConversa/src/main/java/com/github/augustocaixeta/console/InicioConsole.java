package com.github.augustocaixeta.console;

import java.util.Scanner;

public class InicioConsole {
    public static void mostrar(Scanner scanner) {
        int escolha;
        
        do {
            System.out.println("============= MENU INICIAL =============");
            System.out.println("1. Iniciar Sessao");
            System.out.println("2. Criar Conta");
            System.out.println("0. Encerrar");
            System.out.println("============= MENU INICIAL =============");
            System.out.print("R: ");

            escolha = scanner.nextInt();
            scanner.nextLine();
            
            switch (escolha) {
                case 1 -> UsuarioConsole.iniciarSessao(scanner);
                case 2 -> UsuarioConsole.criarConta(scanner);
                case 0 -> System.out.println("Encerrando...");
            }
        } while (escolha != 0);
    }
}
