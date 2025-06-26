package com.github.augustocaixeta.model;

import java.time.LocalDateTime;

public class Conversa {
    private int id;
    private String tipo;
    private LocalDateTime criacao;

    public Conversa(int id, String tipo, LocalDateTime criacao) {
        this.id = id;
        this.tipo = tipo;
        this.criacao = criacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDateTime criacao) {
        this.criacao = criacao;
    }
}
