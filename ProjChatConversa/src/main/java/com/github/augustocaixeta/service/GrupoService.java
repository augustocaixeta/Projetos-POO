package com.github.augustocaixeta.service;

import com.github.augustocaixeta.dao.ConversaDAO;
import com.github.augustocaixeta.model.Grupo;
import com.github.augustocaixeta.model.Mensagem;
import com.github.augustocaixeta.model.Usuario;

import java.util.List;

public class GrupoService {
    public List<Usuario> listarGrupoUsuarios(Grupo grupo) {
        ConversaDAO conversaDao = new ConversaDAO();
        return conversaDao.listarConversaUsuarios(grupo.getConversa());
    }
    
    public List<Mensagem> listarGrupoMensagens(Grupo grupo) {
        ConversaDAO conversaDao = new ConversaDAO();
        return conversaDao.listarConversaMensagens(grupo.getConversa());
    }
}
