package com.github.augustocaixeta.model;

import java.time.LocalDateTime;
import java.util.List;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private List<Contato> contatos;
    private LocalDateTime criacao;
    
    public Usuario(int id, String nome, String email, LocalDateTime criacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.criacao = criacao;
    }
    
    public Usuario(String nome, String email) {
        this.id = id;
        this.nome = nome;
    }
    
    public Usuario() {
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
    
    public LocalDateTime getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDateTime criacao) {
        this.criacao = criacao;
    }
}
