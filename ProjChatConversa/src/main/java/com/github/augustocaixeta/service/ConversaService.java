package com.github.augustocaixeta.service;

import com.github.augustocaixeta.dao.ConversaDAO;
import com.github.augustocaixeta.dao.MensagemDAO;
import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Mensagem;
import com.github.augustocaixeta.model.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConversaService {
    ConversaDAO conversaDAO;
    MensagemDAO mensagemDAO;
    
    public ConversaService() {
        conversaDAO = new ConversaDAO();
        mensagemDAO = new MensagemDAO();
    }
    
    public Conversa encontrarOuCriarConversaIndividual(int usuarioId1, int usuarioId2) {
        if (usuarioId1 == usuarioId2) {
            return conversaDAO.buscarConversaPessoal(usuarioId1);
        }
        
        int menorId = Math.min(usuarioId1, usuarioId2);
        int maiorId = Math.max(usuarioId1, usuarioId2);
        
        Conversa existente = conversaDAO.buscarConversaIndividual(menorId, maiorId);
        if (existente != null) {
            return existente;
        }
        
        Conversa novaConversa = new Conversa();
        novaConversa.setTipo("Individual");
        novaConversa.setCriadaEm(LocalDateTime.now());
        conversaDAO.salvar(novaConversa);
        
        conversaDAO.salvarIndividual(novaConversa.getId(), menorId);
        conversaDAO.salvarIndividual(novaConversa.getId(), maiorId);
        
        return novaConversa;
    }
    
    public List<Mensagem> listarConversaMensagens(Conversa conversa) {
        return conversaDAO.listarMensagens(conversa);
    }
    
    public List<Usuario> listarConversaUsuarios(Conversa conversa) {
        return conversaDAO.listarUsuarios(conversa);
    }
    
    public void enviarMensagem(Usuario autor, Conversa conversa, String conteudo) {
        Mensagem mensagem = new Mensagem();
        mensagem.setAutor(autor);
        mensagem.setConversa(conversa);
        mensagem.setConteudo(conteudo);
        mensagem.setEnviadaEm(LocalDateTime.now());
        mensagemDAO.salvar(mensagem);
    }
    
    public String getCriadaEmFormatada(Conversa conversa) {
        return conversa.getCriadaEm().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
