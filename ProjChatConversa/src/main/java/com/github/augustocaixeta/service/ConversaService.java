package com.github.augustocaixeta.service;

import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Mensagem;
import com.github.augustocaixeta.model.Usuario;
import com.github.augustocaixeta.repository.MensagemRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ConversaService {
    private final MensagemRepository mensagemRepo;
    
    public ConversaService(MensagemRepository mensagemRepo) {
        this.mensagemRepo = mensagemRepo;
    }
    
    public void enviarMensagem(Usuario autor, Conversa conversa, String conteudo) {
        Mensagem m = new Mensagem(0, autor, conversa, conteudo, LocalDateTime.now());
        mensagemRepo.salvar(m);
    }
    
    public List<Mensagem> listarMensagens(Conversa conversa) {
        return mensagemRepo.buscarPorConversa(conversa.getId());
    }
}
