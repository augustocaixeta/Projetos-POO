package com.github.augustocaixeta.model;

import java.time.LocalDateTime;

public class Conversa {
    private int id;
    private String tipo;
    private LocalDateTime criadaEm;

    public Conversa(int id, String tipo, LocalDateTime criacao) {
        this.id = id;
        this.tipo = tipo;
        this.criadaEm = criacao;
    }

    public Conversa() {
        
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

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(LocalDateTime criacao) {
        this.criadaEm = criacao;
    }
}
