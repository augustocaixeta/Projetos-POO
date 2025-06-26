package com.github.augustocaixeta.model;

import java.time.LocalDateTime;

public class Contato {
    private int id;
    private Usuario titular;
    private Usuario contato;
    private String apelido;
    private LocalDateTime criacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getTitular() {
        return titular;
    }

    public void setTitular(Usuario titular) {
        this.titular = titular;
    }

    public Usuario getContato() {
        return contato;
    }

    public void setContato(Usuario contato) {
        this.contato = contato;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    
    public LocalDateTime getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDateTime criacao) {
        this.criacao = criacao;
    }
}
