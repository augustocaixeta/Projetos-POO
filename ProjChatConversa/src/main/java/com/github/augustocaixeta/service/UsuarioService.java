package com.github.augustocaixeta.service;

import com.github.augustocaixeta.dao.UsuarioDAO;
import com.github.augustocaixeta.model.Usuario;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioService() {
        usuarioDAO = new UsuarioDAO();
    }
    
    public int obterUsuarioPeloNome(String nome) {
        return usuarioDAO.obterUsuarioPeloNome(nome).getId();
    }
    
    public Usuario validarAutenticacaoUsuario(String nome, String senha) {
        Usuario usuario = usuarioDAO.obterUsuarioPeloNome(nome);
        System.out.println("Usuario: " + usuario + ", " + "Senha: " + usuario.getSenha());
        if (usuario != null && senha.equals(usuario.getSenha())) {
            return usuario;
        }
        return null;
    }
}
