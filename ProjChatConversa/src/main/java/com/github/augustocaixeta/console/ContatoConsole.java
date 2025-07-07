package com.github.augustocaixeta.console;

import com.github.augustocaixeta.model.Contato;
import com.github.augustocaixeta.model.Usuario;
import com.github.augustocaixeta.service.ContatoService;
import com.github.augustocaixeta.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ContatoConsole {
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final ContatoService contatoService = new ContatoService();
    
    public static void adicionarNovoContato(Usuario usuario, Scanner scanner) {
        System.out.println("Adicionando um contato...");
        System.out.print("Nome do Usuario: ");
        String alvoUsuarioNome = scanner.nextLine();
        Usuario alvoUsuario = usuarioService.obterUsuarioPeloNome(alvoUsuarioNome);
        
        if (alvoUsuario == null) {
            System.out.println("\n<!> Usuario nao encontrado.\n");
            return;
        }
        
        if (alvoUsuario.getId() == usuario.getId()) {
            System.out.println("\n<!> Adicionar a si mesmo nao e permitido.\n");
            return;
        }
        
        System.out.println("Usuario '" + alvoUsuarioNome + "' adicionado!\n");
        Contato contato = new Contato();
        contato.setTitular(usuario);
        contato.setContato(alvoUsuario);
        contato.setAdicionadoEm(LocalDateTime.now());
        contatoService.salvarContato(contato);
    }
    
    public static void mostrarContatos(Usuario usuario, Scanner scanner) {
        List<Usuario> usuarios = usuarioService.listarUsuarioContatos(usuario.getId());
        
        if (usuarios.isEmpty()) {
            System.out.println("\n<!> Voce ainda nao tem nenhum contato adicionado.\n");
            return;
        }
        
        int escolha;
        
        do {
            System.out.println("------------------------------------------");
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println((i + 1) + ". " + usuarios.get(i).getNome());
            }
            System.out.println("0. Retornar ao menu do usuario");
            System.out.println("------------------------------------------");
            System.out.print("R: ");
            
            escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 0) {
                System.out.println("Retornando ao menu...");
            } else if (escolha < 0 || escolha > usuarios.size()) {
                System.out.println("<!> Opção inválida. Tente novamente.");
            } else {
                Usuario contato = usuarios.get(escolha - 1);
                System.out.println("Você selecionou: " + contato.getNome());
            }
        } while (escolha != 0);
    }
}
