package com.github.augustocaixeta.model;

import java.time.LocalDateTime;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private String email;
    private LocalDateTime criadoEm;

    public Usuario(int id, String nome, String senha, String email, LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.criadoEm = criadoEm;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
