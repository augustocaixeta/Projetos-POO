package com.github.augustocaixeta.model;

import java.time.LocalDateTime;

public class Mensagem {
    private int id;
    private Usuario autor;
    private Conversa conversa;
    private String conteudo;
    private LocalDateTime enviadaEm;

    public Mensagem(int id, Usuario autor, Conversa conversa, String conteudo, LocalDateTime enviadaEm) {
        this.id = id;
        this.autor = autor;
        this.conversa = conversa;
        this.conteudo = conteudo;
        this.enviadaEm = enviadaEm;
    }
    
    public Mensagem() {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Conversa getConversa() {
        return conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getEnviadaEm() {
        return enviadaEm;
    }

    public void setEnviadaEm(LocalDateTime enviadaEm) {
        this.enviadaEm = enviadaEm;
    }
}
