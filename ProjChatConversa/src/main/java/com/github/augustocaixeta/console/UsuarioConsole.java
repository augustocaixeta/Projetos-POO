package com.github.augustocaixeta.console;

import com.github.augustocaixeta.model.Usuario;
import com.github.augustocaixeta.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UsuarioConsole {
    private static final UsuarioService usuarioService = new UsuarioService();
    
    public static void mostrarMenuUsuario(Usuario usuario, Scanner scanner) {
        int escolha;
        
        do {
            System.out.println("============= MENU USUARIO ==============");
            System.out.println("1. Iniciar Nova Conversa");
            System.out.println("2. Adicionar Novo Contato");
            System.out.println("3. Meus Contatos");
            System.out.println("4. Minhas Conversas");
            System.out.println("0. Sair");
            System.out.println("============= MENU USUARIO ==============");
            System.out.print("R: ");
            
            escolha = scanner.nextInt();
            scanner.nextLine();
            
            switch (escolha) {
                case 1 -> ConversaConsole.iniciarNovaConversa(usuario, scanner);
                case 2 -> ContatoConsole.adicionarNovoContato(usuario, scanner);
                case 3 -> ContatoConsole.mostrarContatos(usuario, scanner);
                case 4 -> ConversaConsole.mostrarConversas(usuario, scanner);
                case 0 -> System.out.println("Retornando...");
            }
        } while (escolha != 0);
    }
    
    public static void iniciarSessao(Scanner scanner) {
        Usuario usuario = UsuarioConsole.lerCredenciais(scanner);
        if (usuario == null) {
            System.out.println("\n<!> Nome de usuario ou senha invalidos. Tente novamente.\n");
            return;
        }
        
        System.out.println("\nLogin bem-sucedido! Bem-vindo(a), " + usuario.getNome() + "!\n");
        UsuarioConsole.mostrarMenuUsuario(usuario, scanner);
    }
    
    public static void criarConta(Scanner scanner) {
        System.out.print("Usuario: ");
        String nome = scanner.nextLine();
        
        if (usuarioService.checarNomeExistente(nome)) {
            System.out.println("\n<!> Nome de usuario indisponivel. Escolha um nome unico.\n");
            return;
        }
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setEmail(email);
        usuario.setCriadoEm(LocalDateTime.now());
        usuarioService.salvarUsuario(usuario);
        mostrarMenuUsuario(usuario, scanner);
    }

    private static Usuario lerCredenciais(Scanner scanner) {
        System.out.print("Usuario: ");
        String nome = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        return usuarioService.validarAutenticacaoUsuario(nome, senha);
    }
}
