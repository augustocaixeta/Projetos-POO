package com.github.augustocaixeta.console;

import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Usuario;
import com.github.augustocaixeta.service.ConversaService;
import com.github.augustocaixeta.service.UsuarioService;

import java.util.Scanner;

public class MenuUsuario {
    public static void exibirMenuUsuario(Usuario usuario, Scanner scanner) {
        int opcao;
        do {
            System.out.println("============= MENU USUARIO ==============");
            System.out.println("1. Iniciar Nova Conversa");
            System.out.println("2. Minhas Conversas");
            System.out.println("3. Meus Grupos");
            System.out.println("0. Sair");
            System.out.println("============= MENU USUARIO ==============");
            System.out.print("R: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1 -> iniciarNovaConversa(usuario, scanner);
                case 2 -> mostrarUsuarioConversas(usuario);
                case 0 -> System.out.println("Retornando...");
            }
        } while (opcao != 0);
    }
    
    private static void iniciarNovaConversa(Usuario usuario, Scanner scanner) {
        System.out.println("\nIniciando uma nova conversa...");
        System.out.print("Nome do usuario para iniciar a conversa: ");
        String alvoUsuarioNome = scanner.nextLine();

        if (alvoUsuarioNome.equalsIgnoreCase(usuario.getNome())) {
            System.out.println("Voce nao pode iniciar uma conversa consigo mesmo.");
            return;
        }
        
        UsuarioService usuarioService = new UsuarioService();
        ConversaService conversaService = new ConversaService();
        
        Conversa conversa = conversaService.encontrarOuCriarConversaIndividual(
                usuario.getId(),
                usuarioService.obterUsuarioPeloNome(alvoUsuarioNome));
        
        if (conversa != null) {
            System.out.println("\nConversando com " + alvoUsuarioNome + " (ID: " + conversa.getId() + ")...");
            System.out.println("Escreva 'sair' para retornar ao menu do usuario.");
            String conteudo;
            
            do {
                System.out.print(usuario.getNome() + ": ");
                conteudo = scanner.nextLine();
                if (!conteudo.equalsIgnoreCase("sair")) {
                    conversaService.enviarMensagem(usuario, conversa, conteudo);
                    System.out.println("Mensagem enviada!");
                }
            } while (!conteudo.equalsIgnoreCase("sair"));
        } else {
            System.out.println("Nao foi possivel encontrar ou criar a conversa.");
        }
    }
    
    public static void mostrarUsuarioConversas(Usuario usuario) {
        System.out.println("Mostrando conversas do usuario...");
    }
}
