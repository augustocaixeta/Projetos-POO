package com.github.augustocaixeta.console;

import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Mensagem;
import com.github.augustocaixeta.model.Usuario;
import com.github.augustocaixeta.service.ConversaService;
import com.github.augustocaixeta.service.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class ConversaConsole {
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final ConversaService conversaService = new ConversaService();
    
    public static void iniciarNovaConversa(Usuario usuario, Scanner scanner) {
        System.out.println("Iniciando uma conversa...");
        System.out.print("Nome do Usuario: ");
        String alvoUsuarioNome = scanner.nextLine();
        Usuario alvoUsuario = usuarioService.obterUsuarioPeloNome(alvoUsuarioNome);
        
        if (alvoUsuario == null) {
            System.out.println("\n<!> Usuario nao encontrado.\n");
            return;
        }
        
        Conversa conversa = conversaService.encontrarOuCriarConversaIndividual(usuario.getId(), alvoUsuario.getId());
        if (conversa == null) {
            System.out.println("\n<!> Nao foi possivel encontrar ou criar a conversa.\n");
            return;
        }
        
        System.out.println("\nConversando com '" + alvoUsuarioNome + "'...");
        System.out.println("Escreva 'sair' para retornar ao menu do usuario.\n");
        executarConversa(usuario, conversa, scanner);
    }
    
    public static void mostrarConversas(Usuario usuario, Scanner scanner) {
        List<Conversa> conversas = usuarioService.listarUsuarioConversas(usuario.getId());
        
        if (conversas.isEmpty()) {
            System.out.println("\n<!> Voce ainda nao possui nenhuma conversa.\n");
            return;
        }
        
        String nomeExibicao;
        System.out.println("------------------------------------------");
        for (int i = 0; i < conversas.size(); i++) {
            Conversa conversa = conversas.get(i);
            List<Usuario> participantes = conversaService.listarConversaUsuarios(conversa);

            if ("Pessoal".equalsIgnoreCase(conversa.getTipo())) {
                nomeExibicao = "(voce)";
            } else {
                nomeExibicao = "(desconhecido)";
                for (Usuario u : participantes) {
                    if (u.getId() != usuario.getId()) {
                        u.getNome();
                        break;
                    }
                }
            }
            
            System.out.println((i + 1) + ". " + nomeExibicao
                    + "(Criada em: " + conversaService.getCriadaEmFormatada(conversa) + ")");
        }
        
        System.out.println("0. Retornar ao menu do usuario");
        System.out.println("------------------------------------------");
        System.out.print("R: ");
        
        int escolha = scanner.nextInt();
        scanner.nextLine();
        
        if (escolha < 1 || escolha > conversas.size()) {
            System.out.println("<!> Escolha invalida. Por favor, escolha um numero da lista.");
            return;
        }
        
        Conversa conversaSelecionada = conversas.get(escolha - 1);
        mostrarConversaMensagens(conversaSelecionada, usuario, scanner);
    }
    
    private static void mostrarConversaMensagens(Conversa conversa, Usuario usuario, Scanner scanner) {
        List<Mensagem> mensagensAnteriores = conversaService.listarConversaMensagens(conversa);
        
        if (mensagensAnteriores.isEmpty()) {
            System.out.println("\n<!> Nenhuma mensagem nesta conversa ainda.\n");
            return;
        }
        
        System.out.println("------------------------------------------");
        for (Mensagem m : mensagensAnteriores) {
            String autorUsuarioNome = m.getAutor().getNome();
            System.out.println(autorUsuarioNome + " [" + m.getEnviadaEm().toLocalTime() + "]: " + m.getConteudo());
        }
        System.out.println("------------------------------------------");
        System.out.println("Escreva 'sair' para retornar a lista de conversas.");
        executarConversa(usuario, conversa, scanner);
    }
    
    private static void executarConversa(Usuario usuario, Conversa conversa, Scanner scanner) {
        String conteudo;
        do {
            System.out.print("Mensagem: ");
            conteudo = scanner.nextLine();
            if (!conteudo.equalsIgnoreCase("sair")) {
                conversaService.enviarMensagem(usuario, conversa, conteudo);
                System.out.println("Mensagem enviada!");
            }
        } while (!conteudo.equalsIgnoreCase("sair"));
        System.out.println("Saindo da conversa...");
    }
}
