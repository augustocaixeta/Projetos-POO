package com.github.augustocaixeta.service;

import com.github.augustocaixeta.dao.UsuarioDAO;
import com.github.augustocaixeta.model.Conversa;
import com.github.augustocaixeta.model.Usuario;

import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioService() {
        usuarioDAO = new UsuarioDAO();
    }
    
    public void salvarUsuario(Usuario usuario) {
        usuarioDAO.salvar(usuario);
    }
    
    public Usuario obterUsuarioPeloNome(String nome) {
        return usuarioDAO.obterUsuarioPeloNome(nome);
    }
    
    public boolean checarNomeExistente(String nome) {
        return usuarioDAO.obterUsuarioPeloNome(nome) != null;
    }
    
    public Usuario validarAutenticacaoUsuario(String nome, String senha) {
        Usuario usuario = usuarioDAO.obterUsuarioPeloNome(nome);
        if (usuario != null && senha.equals(usuario.getSenha())) {
            return usuario;
        }
        return null;
    }
    
    public List<Usuario> listarUsuarioContatos(int usuarioId) {
        return usuarioDAO.listarUsuarios(usuarioId);
    }
    
    public List<Conversa> listarUsuarioConversas(int usuarioId) {
        return usuarioDAO.listarConversas(usuarioId);
    }
}
