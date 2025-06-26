package com.github.augustocaixeta.model;

import java.time.LocalDateTime;

public class Mensagem {
    private int id;
    private Usuario autor;
    private Conversa conversa;
    private String conteudo;
    private LocalDateTime envio;

    public Mensagem(int id, Usuario autor, Conversa conversa, String conteudo, LocalDateTime envio) {
        this.id = id;
        this.autor = autor;
        this.conversa = conversa;
        this.conteudo = conteudo;
        this.envio = envio;
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

    public LocalDateTime getEnvio() {
        return envio;
    }

    public void setEnvio(LocalDateTime envio) {
        this.envio = envio;
    }
}
