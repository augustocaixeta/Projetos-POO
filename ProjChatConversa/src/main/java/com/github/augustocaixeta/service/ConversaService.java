package com.github.augustocaixeta.service;

import com.github.augustocaixeta.dao.ConversaDAO;
import com.github.augustocaixeta.dao.ConversaParticipanteDAO;
import com.github.augustocaixeta.dao.MensagemDAO;
import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Mensagem;
import com.github.augustocaixeta.model.Usuario;

import java.time.LocalDateTime;

public class ConversaService {
    ConversaDAO conversaDAO;
    ConversaParticipanteDAO conversaParticipanteDAO;
    MensagemDAO mensagemDAO;
    
    public ConversaService() {
        conversaDAO = new ConversaDAO();
        conversaParticipanteDAO = new ConversaParticipanteDAO();
        mensagemDAO = new MensagemDAO();
    }
    
    public Conversa encontrarOuCriarConversaIndividual(int usuarioId1, int usuarioId2) {
        int menorId = Math.min(usuarioId1, usuarioId2);
        int maiorId = Math.max(usuarioId1, usuarioId2);

        Conversa existente = conversaDAO.buscarConversaIndividualEntre(menorId, maiorId);
        if (existente != null) {
            return existente;
        }

        Conversa novaConversa = new Conversa();
        novaConversa.setTipo("individual");
        novaConversa.setCriadaEm(LocalDateTime.now());
        conversaDAO.salvar(novaConversa);

        conversaParticipanteDAO.salvarIndividual(novaConversa.getId(), menorId);
        conversaParticipanteDAO.salvarIndividual(novaConversa.getId(), maiorId);

        return novaConversa;
    }
    
    public void enviarMensagem(Usuario autor, Conversa conversa, String conteudo) {
        Mensagem mensagem = new Mensagem();
        mensagem.setAutor(autor);
        mensagem.setConversa(conversa);
        mensagem.setConteudo(conteudo);
        mensagem.setEnviadaEm(LocalDateTime.now());
        mensagemDAO.salvar(mensagem);
    }
}
