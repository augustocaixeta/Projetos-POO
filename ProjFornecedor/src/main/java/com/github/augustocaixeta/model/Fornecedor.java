package com.github.augustocaixeta.model;

import java.util.List;

public class Fornecedor {
    private int id;
    private String nome;
    private String CNPJ;
    private List<Contato> contatos;

    public Fornecedor(int id, String nome, String CNPJ) {
        this.id = id;
        this.nome = nome;
        this.CNPJ = CNPJ;
    }
    
    public Fornecedor(String nome, String CNPJ) {
        this.nome = nome;
        this.CNPJ = CNPJ;
    }

    public Fornecedor() {
        super();
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

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
    
    @Override
    public String toString() {
        return "ID: " + getId() + "\n"
                + "Nome: " + getNome() + "\n"
                + "CNPJ: " + getCNPJ() + "\n";
    }
}
