package com.github.augustocaixeta.console;

import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Usuario;
import com.github.augustocaixeta.service.ConversaService;
import com.github.augustocaixeta.service.UsuarioService;

import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner scanner;
    private final UsuarioService usuarioService;
    private Usuario usuarioLogado;
    
    public MenuPrincipal() {
        scanner = new Scanner(System.in);
        usuarioService = new UsuarioService();
    }
    
    public void exibirMenuInicial() {
        int opcao;
        do {
            System.out.println("============= MENU INICIAL =============");
            System.out.println("1. Entrar");
            System.out.println("2. Gerenciar");
            System.out.println("0. Encerrar");
            System.out.println("============= MENU INICIAL =============");
            System.out.print("R: ");

            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1 -> {
                    usuarioLogado = realizarLogin();
                    if (usuarioLogado != null) {
                        System.out.println("\nLogin bem-sucedido! Bem-vindo(a), " + usuarioLogado.getNome() + "!\n");
                        exibirMenuUsuario();
                    } else {
                        System.out.println("\n<!> Nome de usuario ou senha invalidos. Tente novamente.\n");
                    }
                }
                case 0 -> System.out.println("Encerrando...");
            }
        } while (opcao != 0);
    }
    
    public Usuario realizarLogin() {
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        return usuarioService.validarAutenticacaoUsuario(usuario, senha);
    }
    
    public void exibirMenuUsuario() {
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
                case 1 -> iniciarNovaConversa();
                case 0 -> System.out.println("Retornando...");
            }
        } while (opcao != 0);
    }
    
    private void iniciarNovaConversa() {
        System.out.println("\nIniciando uma nova conversa...");
        System.out.print("Nome do usuario para iniciar a conversa: ");
        String nomeUsuarioAlvo = scanner.nextLine();

        if (nomeUsuarioAlvo.equalsIgnoreCase(usuarioLogado.getNome())) {
            System.out.println("Voce nao pode iniciar uma conversa consigo mesmo.");
            return;
        }
        
        UsuarioService usuarioService = new UsuarioService();
        ConversaService conversaService = new ConversaService();
        
        Conversa conversa = conversaService.encontrarOuCriarConversaIndividual(
                usuarioLogado.getId(),
                usuarioService.obterUsuarioPeloNome(nomeUsuarioAlvo));
        
        if (conversa != null) {
            System.out.println("\nConversando com " + nomeUsuarioAlvo + " (ID: " + conversa.getId() + ")...");
            System.out.println("Escreva 'sair' para retornar ao menu do usuario.");
            String conteudo;
            
            do {
                System.out.print(usuarioLogado.getNome() + ": ");
                conteudo = scanner.nextLine();
                if (!conteudo.equalsIgnoreCase("sair")) {
                    conversaService.enviarMensagem(usuarioLogado, conversa, conteudo);
                    System.out.println("Mensagem enviada!");
                }
            } while (!conteudo.equalsIgnoreCase("sair"));
        } else {
            System.out.println("Nao foi possivel encontrar ou criar a conversa.");
        }
    }
}
