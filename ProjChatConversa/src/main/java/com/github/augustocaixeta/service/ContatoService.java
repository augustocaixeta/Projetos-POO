package com.github.augustocaixeta.service;

import com.github.augustocaixeta.dao.ContatoDAO;
import com.github.augustocaixeta.model.Contato;

public class ContatoService {
    private final ContatoDAO contatoDAO;
    
    public ContatoService() {
        contatoDAO = new ContatoDAO();
    }
    
    public void salvarContato(Contato contato) {
        contatoDAO.salvar(contato);
    }
}
