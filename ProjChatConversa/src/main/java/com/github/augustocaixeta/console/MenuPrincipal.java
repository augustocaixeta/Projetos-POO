package com.github.augustocaixeta.console;

import com.github.augustocaixeta.model.Usuario;
import com.github.augustocaixeta.service.UsuarioService;

import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner scanner;
    private final UsuarioService usuarioService;
    
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
                    Usuario usuario = solicitarLoginUsuario();
                    if (usuario != null) {
                        System.out.println("\nLogin bem-sucedido! Bem-vindo(a), " + usuario.getNome() + "!\n");
                        MenuUsuario.exibirMenuUsuario(usuario, scanner);
                    } else {
                        System.out.println("\n<!> Nome de usuario ou senha invalidos. Tente novamente.\n");
                    }
                }
                case 0 -> System.out.println("Encerrando...");
            }
        } while (opcao != 0);
    }
    
    private Usuario solicitarLoginUsuario() {
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        return usuarioService.validarAutenticacaoUsuario(usuario, senha);
    }
}
